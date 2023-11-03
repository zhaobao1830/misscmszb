package com.zb.misscmszb.extension.file;

import com.zb.misscmszb.module.file.AbstractUploader;
import com.zb.misscmszb.module.file.FileConfiguration;
import com.zb.misscmszb.module.file.FileConstant;
import com.zb.misscmszb.module.file.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class LocalUploader extends AbstractUploader {

    @Autowired
    private FileConfiguration fileConfiguration;

    /**
     * 获取文件上传配置
     */
    @Override
    protected FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

    /**
     * 获取文件保存路径
     *
     * @param newFilename 文件新名称
     * @return 文件保存路径
     */
    @Override
    protected String getStorePath(String newFilename) {
        Date date = new Date();
        String formattedDate = new SimpleDateFormat("yyyy/MM/dd").format(date);
        Path path = Paths.get(fileConfiguration.getStoreDir(), formattedDate).toAbsolutePath();
        java.io.File file = new File(path.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        return Paths.get(formattedDate, newFilename).toString();
    }

    /**
     * 返回文件存储位置类型
     */
    @Override
    protected String getFileType() {
        return FileConstant.LOCAL;
    }

    /**
     * 处理单个文件
     *
     * @param bytes       文件的字节数
     * @param newFilename 文件的新名称
     * @return 是否处理完成
     */
    @Override
    protected boolean handleOneFile(byte[] bytes, String newFilename) {
        // 获取绝对路径
        String absolutePath = FileUtil.getFileAbsolutePath(fileConfiguration.getStoreDir(), newFilename);
        try {
            BufferedOutputStream stream = new BufferedOutputStream(Files.newOutputStream(Paths.get(absolutePath)));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            log.error("write file to local err:", e);
            // throw new FailedException("read file date failed", 10190);
            return false;
        }
        return true;
    }
}
