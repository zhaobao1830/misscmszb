package com.zb.misscmszb.controller.cms;

import com.zb.misscmszb.core.annotation.LoginRequired;
import com.zb.misscmszb.core.local.LocalUser;
import com.zb.misscmszb.dto.user.LoginDTO;
import com.zb.misscmszb.core.exception.NotFoundException;
import com.zb.misscmszb.core.exception.ParameterException;
import com.zb.misscmszb.extension.token.DoubleJWT;
import com.zb.misscmszb.extension.token.Tokens;
import com.zb.misscmszb.model.UserDO;
import com.zb.misscmszb.service.GroupService;
import com.zb.misscmszb.service.UserIdentityService;
import com.zb.misscmszb.service.UserService;
import com.zb.misscmszb.vo.UserPermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cms/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserIdentityService userIdentityService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private DoubleJWT jwt;

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
}
