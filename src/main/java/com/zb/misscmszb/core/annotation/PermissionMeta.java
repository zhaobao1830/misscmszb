package com.zb.misscmszb.core.annotation;

import java.lang.annotation.*;

/**
 * 路由信息，记录路由权限、模块等信息
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionMeta {

    String value();

    String permission() default "";

    String module() default "";

    boolean mount() default true;
}
