package com.zb.misscmszb.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@TableName("lin_file")
@EqualsAndHashCode(callSuper = true)
public class FileDO extends BaseModel implements Serializable {
    private String path;
    /**
     * LOCAL REMOTE
     */
    private String type;

    private String name;

    private String extension;

    private Integer size;

    /**
     * md5值，防止上传重复文件
     */
    private String md5;
}
