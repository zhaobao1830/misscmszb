package com.zb.misscmszb.core.configuration;

import com.zb.misscmszb.core.interceptors.AuthorizeInterceptor;
import com.zb.misscmszb.core.interceptors.LogInterceptor;
import com.zb.misscmszb.extension.token.DoubleJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * cms配置文件
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(CmsProperties.class)
public class CmsConfiguration {

    @Autowired
    private CmsProperties properties;

    // 调用DoubleJWT的构造函数，生成DoubleJWT对象，并通过@Bean注解注入到容器里
    @Bean
    public DoubleJWT jwt() {
        String secret = properties.getTokenSecret();
        Long accessExpire = properties.getTokenAccessExpire();
        Long refreshExpire = properties.getTokenRefreshExpire();

        if (accessExpire == null) {
            accessExpire = 60 * 60L;
        }

        if (refreshExpire == null) {
            // 一个月
            refreshExpire = 60 * 60 * 24 * 30L;
        }

        return new DoubleJWT(secret, accessExpire, refreshExpire);
    }

    // 调用AuthorizeInterceptor的构造函数，生成AuthorizeInterceptor对象，并通过@Bean注解注入到容器里
    @Bean
    public AuthorizeInterceptor authInterceptor() {
        String[] excludeMethods = properties.getExcludeMethods();
        return new AuthorizeInterceptor(excludeMethods);
    }

    @Bean
    @ConditionalOnProperty(prefix = "zb.cms", value = "logger-enabled", havingValue = "true")
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }
}
