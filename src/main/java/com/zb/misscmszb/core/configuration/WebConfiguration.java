package com.zb.misscmszb.core.configuration;

import com.zb.misscmszb.core.interceptors.AuthorizeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Value("${auth.enabled:false}")
    private boolean authEnabled;

    @Value("${cms.file.serve-path:assets/**}")
    private String servePath;

    @Autowired
    private AuthorizeInterceptor authorizeInterceptor;

    // 将自定义的拦截类添加到拦截器中
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (authEnabled) {
            //开发环境忽略签名认证
            registry.addInterceptor(authorizeInterceptor)
                    .excludePathPatterns(getDirServePath());
        }
    }

    private String getDirServePath() {
        // assets/**
        // assets/
        // /usr/local/assets/
        // assets
        return servePath;
    }
}
