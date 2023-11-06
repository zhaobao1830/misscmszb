package com.zb.misscmszb.module.file;

/**
 * 文件前预处理器
 */
public interface UploadHandler {
    /**
     * 在文件写入本地或者上传到云之前调用此方法
     *
     * @param fileObj 文件对象
     * @return 是否上传，若返回false，则不上传
     */
    boolean preHandle(FileObj fileObj);

    /**
     * 在文件写入本地或者上传到云之后调用此方法
     *
     * @param fileObj 文件对象
     */
    void afterHandle(FileObj fileObj);
}
