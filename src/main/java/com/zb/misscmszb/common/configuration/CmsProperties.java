package com.zb.misscmszb.common.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * cms 配置属性
 */
@ConfigurationProperties(prefix = "zb.cms")
public class CmsProperties {

    private static final String[] DEFAULT_EXCLUDE_METHODS = new String[]{"OPTIONS"};
    private String tokenSecret = "";

    private String[] excludeMethods = DEFAULT_EXCLUDE_METHODS;

    private Long tokenAccessExpire = 3600L;

    private Long tokenRefreshExpire = 2592000L;

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    public Long getTokenAccessExpire() {
        return tokenAccessExpire;
    }

    /**
     * 设置 access token 过期时间
     *
     * @param tokenAccessExpire 过期时间
     */
    public void setTokenAccessExpire(Long tokenAccessExpire) {
        this.tokenAccessExpire = tokenAccessExpire;
    }

    public Long getTokenRefreshExpire() {
        return tokenRefreshExpire;
    }

    /**
     * 设置 refresh token 过期时间
     *
     * @param tokenRefreshExpire 过期时间
     */
    public void setTokenRefreshExpire(Long tokenRefreshExpire) {
        this.tokenRefreshExpire = tokenRefreshExpire;
    }

    public String[] getExcludeMethods() {
        return excludeMethods;
    }

    public void setExcludeMethods(String[] excludeMethods) {
        this.excludeMethods = excludeMethods;
    }
}
