package com.zb.misscmszb.common.configuration;

import com.zb.misscmszb.extension.token.DoubleJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(CmsProperties.class)
public class CmsConfiguration {

    @Autowired
    private CmsProperties properties;

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
}
