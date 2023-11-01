package com.zb.misscmszb.module.file;

import org.springframework.util.unit.DataSize;

/**
 * 上传文件方法的工具类
 */
public class FileUtil {
    // 将字符串表示的数据大小解析为DataSize对象，从而进行相关的数据大小操作和比较
    public static Long parseSize(String size) {
        DataSize singleLimitData = DataSize.parse(size);
        return singleLimitData.toBytes();
    }
}
