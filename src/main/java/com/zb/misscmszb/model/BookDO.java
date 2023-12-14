package com.zb.misscmszb.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 图书数据对象
 */
@Data
@TableName("book")
@EqualsAndHashCode(callSuper = true)
public class BookDO extends BaseModel implements Serializable {

    private String title;

    private String author;

    private String summary;

    private String image;
}
