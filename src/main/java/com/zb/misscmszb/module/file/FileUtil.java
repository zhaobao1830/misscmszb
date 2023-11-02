package com.zb.misscmszb.module.file;

import org.springframework.util.DigestUtils;
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

    /**
     * 获取文件的后缀名
     * @param fileName 文件名称
     * @return 文件后缀名
     */
    public static String getFileExt(String fileName) {
        int index = fileName.lastIndexOf(".");
        return fileName.substring(index);
    }

    /**
     * 获取文件的md5值
     * @param bytes 文件的字节数
     * @return 文件的md5值
     */
    public static String getFileMD5(byte[] bytes) {
        return DigestUtils.md5DigestAsHex(bytes);
    }
}
