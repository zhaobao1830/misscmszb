package com.zb.misscmszb.core.annotation;

import java.lang.annotation.*;

/**
 * 权限模块注解，打在控制器类上，可用于省略 @PermissionMeta 注解的 module 参数
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionModule {

    String value() default "";
}
