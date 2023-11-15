package com.zb.misscmszb.core.annotation;

import com.zb.misscmszb.core.enumeration.UserLevel;

import java.lang.annotation.*;

/**
 * AdminRequired 和 PermissionMeta 融合注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Required(level = UserLevel.ADMIN)
public @interface AdminMeta {

    String value() default "";

    String permission() default "";

    String module() default "";

    boolean mount() default true;
}
