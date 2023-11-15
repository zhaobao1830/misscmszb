package com.zb.misscmszb.core.enumeration;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * 分组级别枚举类
 */
public enum GroupLevelEnum implements IEnum<Integer> {

    /**
     * 超级管理员
     */
    ROOT(1),
    /**
     * 游客
     */
    GUEST(2),
    /**
     * 普通用户
     */
    USER(3);

    private final Integer value;

    GroupLevelEnum(Integer value) {
        this.value = value;
    }

    /**
     * MybatisEnumTypeHandler 转换时调用此方法
     * @return
     */
    @Override
    public Integer getValue() {
        return this.value;
    }
}
