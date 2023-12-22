package com.zb.misscmszb.dto.log;

import com.zb.misscmszb.dto.query.BasePageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 日志查询数据传输对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryLogDTO extends BasePageDTO {

    protected static Integer defaultCount = 12;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date start;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date end;

    private String name;

    private String keyword;
}
