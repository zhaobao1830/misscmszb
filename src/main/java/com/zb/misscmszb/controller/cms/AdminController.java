package com.zb.misscmszb.controller.cms;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zb.misscmszb.core.annotation.AdminRequired;
import com.zb.misscmszb.core.annotation.PermissionMeta;
import com.zb.misscmszb.core.annotation.PermissionModule;
import com.zb.misscmszb.dto.admin.*;
import com.zb.misscmszb.model.GroupDO;
import com.zb.misscmszb.model.PermissionDO;
import com.zb.misscmszb.model.UserDO;
import com.zb.misscmszb.service.AdminService;
import com.zb.misscmszb.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 管理员
 */
@RestController
@RequestMapping("/cms/admin")
@PermissionModule(value = "管理员")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @AdminRequired
    @GetMapping("/users")
    @PermissionMeta(value = "查询所有用户", mount = false)
    public PageResponseVO<UserInfoVO> getUsers(
            @Validated QueryUsersDTO dto) {
        IPage<UserDO> iPage = adminService.getUserPageByGroupId(dto.getGroupId(), dto.getCount(), dto.getPage());
        List<UserInfoVO> userInfos = iPage.getRecords().stream().map(user -> {
            List<GroupDO> groups = groupService.getUserGroupsByUserId(user.getId());
            return new UserInfoVO(user, groups);
        }).collect(Collectors.toList());
        return PageUtil.build(iPage, userInfos);
    }

    @AdminRequired
    @PutMapping("/user/{id}/password")
    @PermissionMeta(value = "修改用户密码", mount = false)
    public UpdatedVO changeUserPassword(@PathVariable @Positive(message = "{id.positive}") Integer id, @RequestBody @Validated ResetPasswordDTO validator) {
        adminService.changeUserPassword(id, validator);
        return new UpdatedVO(4);
    }

    @AdminRequired
    @GetMapping("/user/{id}")
    @PermissionMeta(value = "删除用户", mount = false)
    public DeletedVO deleteUser(@PathVariable @Positive(message = "{id.positive}") Integer id) {
        adminService.deleteUser(id);
        return new DeletedVO(5);
    }

    @AdminRequired
    @PostMapping("/user/update")
    @PermissionMeta(value = "管理员更新用户信息", mount = false)
    public UpdatedVO updateUser(@RequestBody @Validated UpdateUserInfoDTO validator) {
        adminService.updateUserInfo(validator);
        return new UpdatedVO(6);
    }

    @AdminRequired
    @GetMapping("/permission")
    @PermissionMeta(value = "查询所有可分配的权限", mount = false)
    public Map<String, List<PermissionDO>> getAllPermissions() {
        return adminService.getAllStructuralPermissions();
    }

    @AdminRequired
    @GetMapping("/group/all")
    @PermissionMeta(value = "查询所有权限组", mount = false)
    public List<GroupDO> getAllGroup() {
        return adminService.getAllGroups();
    }

    @AdminRequired
    @GetMapping("/group/delete/{id}")
    @PermissionMeta(value = "删除一个权限组", mount = false)
    public DeletedVO deleteGroup(@PathVariable @Positive(message = "{id.positive}") Integer id) {
        adminService.deleteGroup(id);
        return new DeletedVO(8);
    }

    @AdminRequired
    @PostMapping("/group/update")
    @PermissionMeta(value = "更新一个权限组", mount = false)
    public UpdatedVO updateGroup(@RequestBody @Validated UpdateGroupDTO validator) {
        adminService.updateGroup(validator);
        return new UpdatedVO(7);
    }

    @AdminRequired
    @GetMapping("/group/{id}")
    @PermissionMeta(value = "查询一个权限组及其权限", mount = false)
    public GroupPermissionVo getGroup(@PathVariable @Positive(message = "{id.positive}") Integer id) {
        return adminService.getGroup(id);
    }

    @AdminRequired
    @PostMapping("/group/create")
    @PermissionMeta(value = "新建权限组", mount = false)
    public CreatedVO createGroup(@RequestBody @Validated NewGroupDTO validator) {
        adminService.createGroup(validator);
        return new CreatedVO(15);
    }

    @AdminRequired
    @PostMapping("/permission/dispatch/batch")
    @PermissionMeta(value = "分配多个权限", mount = false)
    public CreatedVO dispatchPermissions(@RequestBody @Validated DispatchPermissionsDTO validator) {
        adminService.dispatchPermissions(validator);
        return new CreatedVO(9);
    }

    @AdminRequired
    @PostMapping("/permission/remove")
    @PermissionMeta(value = "删除多个权限", mount = false)
    public DeletedVO removePermissions(@RequestBody @Validated RemovePermissionsDTO validator) {
        adminService.removePermissions(validator);
        return new DeletedVO(10);
    }
}
