package com.zb.misscmszb.module.file;

import org.springframework.util.DigestUtils;
import org.springframework.util.unit.DataSize;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * 上传文件方法的工具类
 */
public class FileUtil {
    /**
     * 将字符串表示的数据大小解析为DataSize对象，从而进行相关的数据大小操作和比较
     */
    public static Long parseSize(String size) {
        DataSize singleLimitData = DataSize.parse(size);
        return singleLimitData.toBytes();
    }

    /**
     * 获取主机操作系统的默认文件系统
     */
    public static FileSystem getDefaultFileSystem() {
        return FileSystems.getDefault();
    }

    /**
     * 是否是绝对路径
     * @param dir 文件夹名称
     * @return 是否是绝对路径
     */
    public static boolean isAbsolute(String dir) {
        Path path = getDefaultFileSystem().getPath(dir);
        return path.isAbsolute();
    }

    /**
     * 获取当前工作目录
     */
    public static String getCmd() {
        return System.getProperty("user.dir");
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

    /**
     * 获取文件绝对路径
     * @param dir 文件夹名称
     * @param fileName 文件名称
     * @return 文件路径
     */
    public static  String getFileAbsolutePath(String dir, String fileName) {
        if (isAbsolute(dir)) {
            return getDefaultFileSystem()
                    .getPath(dir, fileName)
                    .toAbsolutePath()
                    .toString();
        } else {
            return getDefaultFileSystem()
                    .getPath(getCmd(), dir, fileName)
                    .toAbsolutePath()
                    .toString();
        }
    }
}
