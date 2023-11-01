package com.zb.misscmszb.module.file;

import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * 文件上传服务接口
 */
public interface Uploader {
    /**
     * 上传文件
     * @param fileMap 文件map
     * @return 文件数据
     */
    List<File> upload(MultiValueMap<String, MultipartFile> fileMap);

    List<File> upload(MultiValueMap<String, MultipartFile> fileMap, PreHandler preHandler);
}
