package com.zb.misscmszb.dto.admin;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * 删除权限数据传输对象
 */
@Data
public class RemovePermissionsDTO {

    @Positive(message = "{group.id.positive}")
    @NotNull(message = "{group.id.not-null}")
    private Integer groupId;

    private List<Integer> permissionIds;
}
