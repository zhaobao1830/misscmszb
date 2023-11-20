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

    // Jackson自定义ObjectMapper配置，现在移到了application里进行配置
//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer customJackson() {
//        return jacksonObjectMapperBuilder -> {
//            // 前端传递的参数，在对象里没有定义，false不会序列化，true会序列化（报错，提示对象里没有定义）
//            jacksonObjectMapperBuilder.failOnUnknownProperties(true);
//            // 返回的属性驼峰转换为小写加下划线
//            jacksonObjectMapperBuilder.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
//        };
//    }
}
