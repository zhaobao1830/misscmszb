package com.zb.misscmszb.dto.admin;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

/**
 * 用户信息更新数据传输对象
 */
@Data
public class UpdateUserInfoDTO {

    @NotNull(message = "{user.id.not-blank}")
    @Positive(message = "{user.id.positive}")
    private Integer id;

    @NotEmpty(message = "{group.ids.not-empty}")
    private List<@Min(1) Integer> groupIds;

}
