package com.zb.misscmszb.core.configuration;

import com.zb.misscmszb.core.interceptors.AuthorizeInterceptor;
import com.zb.misscmszb.core.interceptors.LogInterceptor;
import com.zb.misscmszb.core.interceptors.RequestLogInterceptor;
import com.zb.misscmszb.module.file.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.FileSystems;
import java.nio.file.Path;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Value("${auth.enabled:false}")
    private boolean authEnabled;

    @Value("${request-log.enabled:false}")
    private boolean requestLogEnabled;

    @Value("${cms.file.store-dir:assets/}")
    private String dir;

    @Value("${cms.file.serve-path:assets/**}")
    private String servePath;

    @Autowired
    private AuthorizeInterceptor authorizeInterceptor;

    @Autowired
    private RequestLogInterceptor requestLogInterceptor;

    @Autowired
    private LogInterceptor logInterceptor;

    /**
     * 跨域
     * 注意： 跨域问题涉及安全性问题，这里提供的是最方便简单的配置，任何host和任何方法都可跨域
     * 但在实际场景中，这样做，无疑很危险，所以谨慎选择开启或者关闭
     * 如果切实需要，请咨询相关安全人员或者专家进行配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

    // 将自定义的拦截类添加到拦截器中
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (authEnabled) {
            //开发环境忽略签名认证
            registry.addInterceptor(authorizeInterceptor)
                    .excludePathPatterns(getDirServePath());
        }
        if (requestLogEnabled) {
            registry.addInterceptor(requestLogInterceptor);
        }
        registry.addInterceptor(logInterceptor);
    }

    /**
     * 访问静态资源文件配置
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // classpath: or file:
        // 如果静态资源访问的路径是getDirServePath()返回的值，就映射到访问本地的addResourceLocations的参数路径上
        registry.addResourceHandler(getDirServePath())
                .addResourceLocations("file:" + getAbsDir() + "/");
    }

    private String getDirServePath() {
        // assets/**
        // assets/
        // /usr/local/assets/
        // assets
        return servePath;
    }

    /**
     * 获得文件夹的绝对路径
     */
    private String getAbsDir() {
        if (FileUtil.isAbsolute(dir)) {
            return dir;
        }
        String cmd = System.getProperty("user.dir");
        Path path = FileSystems.getDefault().getPath(cmd, dir);
        return path.toAbsolutePath().toString();
    }
}
