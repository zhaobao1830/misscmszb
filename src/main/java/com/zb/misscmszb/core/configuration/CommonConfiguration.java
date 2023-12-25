package com.zb.misscmszb.core.configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.zb.misscmszb.bean.PermissionMetaCollector;
import com.zb.misscmszb.core.interceptors.RequestLogInterceptor;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 公共配置
 */
@Configuration
public class CommonConfiguration {

    @Bean
    public RequestLogInterceptor requestLogInterceptor() {
        return new RequestLogInterceptor();
    }

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

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则
     * 只有加上这个，查询后IPage里的total才能有值，不然就是0
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
