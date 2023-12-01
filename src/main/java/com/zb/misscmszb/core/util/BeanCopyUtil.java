package com.zb.misscmszb.core.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.util.Arrays;
import java.util.Objects;

@Slf4j
public class BeanCopyUtil extends BeanUtils {

    public static void copyNonNullProperties(Object source, Object target) {
        String[] properties = Arrays.stream(ReflectionUtils.getDeclaredMethods(source.getClass()))
                .map(method -> {
                    if (method.getName().startsWith("get")) {
                        Object fieldValue = null;
                        try {
                            fieldValue = method.invoke(source);
                        } catch (Exception e) {
                            log.error(e.getMessage(), e);
                        }
                        if (fieldValue == null) {
                            String fieldName = method.getName().substring(3);
                            return com.baomidou.mybatisplus.core.toolkit.StringUtils.firstToLowerCase(fieldName);
                        }
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .toArray(String[]::new);
        copyProperties(source, target, properties);
    }
}
