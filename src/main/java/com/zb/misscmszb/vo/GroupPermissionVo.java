package com.zb.misscmszb.vo;

import com.zb.misscmszb.model.GroupDO;
import com.zb.misscmszb.model.PermissionDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 用户 + 权限 view object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupPermissionVo{

    private Integer id;

    private String name;

    private String info;

    private List<PermissionDO> permissions;

    public GroupPermissionVo(GroupDO groupDO, List<PermissionDO> permissions) {
        BeanUtils.copyProperties(groupDO, this);
        this.permissions = permissions;
    }
}
