package com.zb.misscmszb.core.configuration;

import com.zb.misscmszb.bean.PermissionMetaCollector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 公共配置
 */
@Configuration
public class CommonConfiguration {

    /**
     * 记录每个被 @PermissionMeta 记录的信息，在beans的后置调用
     */
    @Bean
    public PermissionMetaCollector postProcessBeans() {
        return new PermissionMetaCollector();
    }
}
