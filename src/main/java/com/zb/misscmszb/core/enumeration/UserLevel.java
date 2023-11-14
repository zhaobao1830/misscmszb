package com.zb.misscmszb.core.enumeration;

/**
 * 用户等级
 */
public enum UserLevel {

    /**
     * 游客即可访问
     */
    TOURIST,

    /**
     * 登录才可访问
     */
    LOGIN,

    /**
     * 登录有权限才可访问
     */
    GROUP,

    /**
     * 管理员权限
     */
    ADMIN,

    /**
     * 令牌刷新
     */
    REFRESH
}
