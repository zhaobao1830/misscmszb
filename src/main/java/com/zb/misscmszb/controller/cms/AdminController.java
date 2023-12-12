package com.zb.misscmszb.controller.cms;

import com.zb.misscmszb.core.annotation.AdminRequired;
import com.zb.misscmszb.core.annotation.PermissionMeta;
import com.zb.misscmszb.core.annotation.PermissionModule;
import com.zb.misscmszb.dto.admin.UpdateGroupDTO;
import com.zb.misscmszb.model.GroupDO;
import com.zb.misscmszb.service.AdminService;
import com.zb.misscmszb.vo.DeletedVO;
import com.zb.misscmszb.vo.UpdatedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

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
}
