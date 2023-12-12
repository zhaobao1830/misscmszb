package com.zb.misscmszb.dto.admin;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * 分组更新数据传输对象
 */
@Data
public class UpdateGroupDTO {

    @Positive(message = "{group.id.positive}")
    @NotNull(message = "{group.id.not-null}")
    private Integer id;

    @Length(min = 1, max = 60, message = "{group.name.length}")
    private String name;

    @Length(max = 255, message = "{group.info.length}")
    private String info;
}
