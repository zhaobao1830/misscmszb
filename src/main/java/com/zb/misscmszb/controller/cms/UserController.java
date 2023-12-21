package com.zb.misscmszb.controller.cms;

import com.zb.misscmszb.core.annotation.AdminRequired;
import com.zb.misscmszb.core.annotation.LoginRequired;
import com.zb.misscmszb.core.annotation.RefreshRequired;
import com.zb.misscmszb.core.local.LocalUser;
import com.zb.misscmszb.dto.user.ChangePasswordDTO;
import com.zb.misscmszb.dto.user.LoginDTO;
import com.zb.misscmszb.core.exception.NotFoundException;
import com.zb.misscmszb.core.exception.ParameterException;
import com.zb.misscmszb.dto.user.RegisterDTO;
import com.zb.misscmszb.dto.user.UpdateInfoDTO;
import com.zb.misscmszb.extension.token.DoubleJWT;
import com.zb.misscmszb.extension.token.Tokens;
import com.zb.misscmszb.model.GroupDO;
import com.zb.misscmszb.model.UserDO;
import com.zb.misscmszb.service.GroupService;
import com.zb.misscmszb.service.UserIdentityService;
import com.zb.misscmszb.service.UserService;
import com.zb.misscmszb.vo.CreatedVO;
import com.zb.misscmszb.vo.UpdatedVO;
import com.zb.misscmszb.vo.UserInfoVO;
import com.zb.misscmszb.vo.UserPermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cms/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserIdentityService userIdentityService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private DoubleJWT jwt;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    @AdminRequired
    public CreatedVO register(@RequestBody @Validated RegisterDTO validator) {
        userService.createUser(validator);
        return new CreatedVO(11);
    }

    @PostMapping("/login")
    public Tokens login(@RequestBody LoginDTO loginDTO) {
        UserDO user = userService.getUserByUsername(loginDTO.getUsername());
        if (user == null) {
            throw new NotFoundException(10021);
        }
        boolean valid = userIdentityService.verifyUsernamePassword(
                user.getId(),
                user.getUsername(),
                loginDTO.getPassword());
        if (!valid) {
            throw new ParameterException(10031);
        }
        return jwt.generateTokens(user.getId());
    }

    /**
     * 查询拥有权限
     */
    @GetMapping("/permissions")
    @LoginRequired
    public UserPermissionVO getPermissions() {
        UserDO user = LocalUser.getLocalUser();
        boolean admin = groupService.checkIsRootByUserId(user.getId());
        List<Map<String, List<Map<String, String>>>> permissions = userService.getStructuralUserPermissions(user.getId());
        UserPermissionVO userPermissions = new UserPermissionVO(user, permissions);
        userPermissions.setAdmin(admin);
        return userPermissions;
    }

    @PostMapping("/update")
    @LoginRequired
    public UpdatedVO update(@RequestBody @Validated UpdateInfoDTO validator) {
        userService.updateUserInfo(validator);
        return new UpdatedVO(6);
    }

    @GetMapping("/information")
    @LoginRequired
    public UserInfoVO getInformation() {
        UserDO user = LocalUser.getLocalUser();
        List<GroupDO> groups = groupService.getUserGroupsByUserId(user.getId());
        return new UserInfoVO(user, groups);
    }

    /**
     * 刷新令牌
     */
    @GetMapping("/refresh")
    @RefreshRequired
    public Tokens getRefreshToken() {
        UserDO user = LocalUser.getLocalUser();
        return jwt.generateTokens(user.getId());
    }

    /**
     * 修改密码
     */
    @PostMapping("/change_password")
    @LoginRequired
    public UpdatedVO updatePassword(@RequestBody @Validated ChangePasswordDTO validator) {
        userService.changeUserPassword(validator);
        return new UpdatedVO(4);
    }
}
