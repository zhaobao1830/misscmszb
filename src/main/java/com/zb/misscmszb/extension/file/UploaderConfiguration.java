package com.zb.misscmszb.extension.file;

import com.zb.misscmszb.module.file.Uploader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 文件上传配置类
 */
@Configuration(proxyBeanMethods = false)
public class UploaderConfiguration {
    @Bean
    @Order
    @ConditionalOnMissingBean
    public Uploader uploader() {
        return new LocalUploader();
    }
}
