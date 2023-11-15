package com.zb.misscmszb.core.annotation;

import com.zb.misscmszb.core.enumeration.UserLevel;

import java.lang.annotation.*;

/**
 *  LoginRequired 和 PermissionMeta 融合注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Required(level = UserLevel.LOGIN)
public @interface LoginMeta {

    String value() default "";

    String permission() default "";

    String module() default "";

    boolean mount() default true;
}
