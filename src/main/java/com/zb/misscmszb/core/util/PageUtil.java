package com.zb.misscmszb.core.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zb.misscmszb.vo.PageResponseVO;

import java.util.List;

/**
 * 分页工具类
 */
public class PageUtil {

    private PageUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> PageResponseVO<T> build(IPage<T> iPage) {
        return new PageResponseVO<>(Math.toIntExact(iPage.getTotal()), iPage.getRecords(),
                Math.toIntExact(iPage.getCurrent()), Math.toIntExact(iPage.getSize()));
    }

    public static <K, T> PageResponseVO<K> build(IPage<T> iPage, List<K> records) {
        return new PageResponseVO<>(Math.toIntExact(iPage.getTotal()), records,
                Math.toIntExact(iPage.getCurrent()),
                Math.toIntExact(iPage.getSize()));
    }

}
