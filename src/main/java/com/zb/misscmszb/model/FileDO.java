package com.zb.misscmszb.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@TableName("lin_file")
@EqualsAndHashCode(callSuper = true)
public class FileDO extends BaseModel implements Serializable {
    /**
     * 文件保存路径
     */
    private String path;
    /**
     * LOCAL REMOTE
     */
    private String type;

    private String name;

    /**
     * 文件后缀名
     */
    private String extension;

    private Integer size;

    /**
     * md5值，防止上传重复文件
     */
    private String md5;
}
