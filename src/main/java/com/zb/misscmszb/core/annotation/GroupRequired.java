package com.zb.misscmszb.core.annotation;

import com.zb.misscmszb.core.enumeration.UserLevel;

import java.lang.annotation.*;

/**
 * 分组权限
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Required(level = UserLevel.GROUP)
public @interface GroupRequired {
}
