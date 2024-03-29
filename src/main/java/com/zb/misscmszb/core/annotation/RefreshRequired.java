package com.zb.misscmszb.core.annotation;

import com.zb.misscmszb.core.enumeration.UserLevel;

import java.lang.annotation.*;

/**
 * 刷新令牌权限
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Required(level = UserLevel.REFRESH)
public @interface RefreshRequired {
}
