package com.zb.misscmszb.dto.admin;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * 用户信息更新数据传输对象
 */
@Data
public class UpdateUserInfoDTO {

    @NotBlank(message = "{user.id.not-blank}")
    @Positive(message = "{user.id.positive}")
    private Integer id;

    @NotEmpty(message = "{group.ids.not-empty}")
    private List<@Min(1) Integer> groupIds;

}
