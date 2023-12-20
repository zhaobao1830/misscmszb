package com.zb.misscmszb.dto.admin;

import com.zb.misscmszb.dto.query.BasePageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;

/**
 * 用户查询数据传输对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryUsersDTO extends BasePageDTO {

    @Min(value = 1, message = "{group.id.positive}")
    private Integer groupId;
}
