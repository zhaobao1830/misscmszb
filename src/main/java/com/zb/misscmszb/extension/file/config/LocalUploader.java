package com.zb.misscmszb.extension.file.config;

import com.zb.misscmszb.module.file.AbstractUploader;
import com.zb.misscmszb.module.file.FileConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

public class LocalUploader extends AbstractUploader {

    @Autowired
    private FileConfiguration configuration;

    /**
     * 获取文件上传配置
     */
    @Override
    protected FileConfiguration getFileConfiguration() {
        return configuration;
    }
}
