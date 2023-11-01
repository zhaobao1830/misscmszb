package com.zb.misscmszb.bo;

import lombok.Data;

/**
 * 定义上传文件接口返回的信息
 */
@Data
public class FileBO {
    /**
     * 文件 id
     */
    private Integer id;

    /**
     * 文件 key，上传时指定的
     */
    private String key;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件 URL
     */
    private String url;
}
