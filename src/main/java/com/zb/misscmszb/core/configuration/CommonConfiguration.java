package com.zb.misscmszb.core.configuration;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.zb.misscmszb.bean.PermissionMetaCollector;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
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

    /**
     * 接口中，自动转换的有：驼峰转换为下划线，空值输出null
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customJackson() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.failOnUnknownProperties(false);
            jacksonObjectMapperBuilder.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        };
    }
}
