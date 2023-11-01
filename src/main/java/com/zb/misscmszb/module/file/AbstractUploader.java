package com.zb.misscmszb.module.file;

import io.github.talelin.autoconfigure.exception.FileTooManyException;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件上传类的基类
 * 模版模式
 */
public abstract class AbstractUploader implements Uploader{

    /**
     * 获取文件上传配置
     */
    protected abstract FileConfiguration getFileConfiguration();

    /**
     * 上传文件
     *
     * @param fileMap 文件map
     * @return 文件数据
     */
    @Override
    public List<File> upload(MultiValueMap<String, MultipartFile> fileMap) {
        // 校验上传文件是否为空和数量
        checkFileMap(fileMap);
        return null;
    }

    @Override
    public List<File> upload(MultiValueMap<String, MultipartFile> fileMap, PreHandler preHandler) {
        return null;
    }

    private void checkFileMap(MultiValueMap<String, MultipartFile> fileMap) {
        if (fileMap.isEmpty()) {
            throw new NotFoundException(10026,  "file not found");
        }
        int nums = getFileConfiguration().getNums();
        if (fileMap.size() > nums) {
            throw new FileTooManyException(10180, "too many files, amount of files must less than" + nums);
        }
    }

    private List<File> handleMultipartFiles(MultiValueMap<String, MultipartFile> fileMap) {
        long singleFileLimit = getSingleFileLimit();
        List<File> fileList = new ArrayList<>();
        fileMap.keySet().forEach(key -> fileMap.get(key).forEach(file -> {
                    if (!file.isEmpty()) {

                    }
        }));
    }

    // 获取单个上传文件的大小
    private long getSingleFileLimit() {
        String singleLimit = getFileConfiguration().getSingleLimit();
        return FileUtil.parseSize(singleLimit);
    }

    /**
     * 初始化存储文件夹
     * @param fileList 文件列表
     * @param singleFileLimit 单个文件的最大值
     * @param file 上传的文件
     */
    private void handleFile(List<File> fileList, long singleFileLimit, MultipartFile file) {

    }
}
