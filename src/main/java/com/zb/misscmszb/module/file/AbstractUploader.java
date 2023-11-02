package com.zb.misscmszb.module.file;

import io.github.talelin.autoconfigure.exception.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传类的基类
 * 模版模式
 */
public abstract class AbstractUploader implements Uploader{
    // 预处理器
    private PreHandler preHandler;

    /**
     * 获取文件上传配置
     */
    protected abstract FileConfiguration getFileConfiguration();

    /**
     * 获取文件保存路径
     * @param newFilename 文件新名称
     * @return 文件保存路径
     */
    protected abstract String getStorePath(String newFilename);

    /**
     * 返回文件存储位置类型
     */
    protected abstract String getFileType();

    /**
     * 处理单个文件
     * @param bytes 文件的字节数
     * @param newFilename 文件的新名称
     * @return 是否处理完成
     */
    protected abstract boolean handleOneFile(byte[] bytes, String newFilename);

    /**
     * 上传文件
     *
     * @param fileMap 文件map
     * @return 文件数据
     */
    @Override
    public List<FileData> upload(MultiValueMap<String, MultipartFile> fileMap) {
        // 校验上传文件是否为空和数量
        checkFileMap(fileMap);
        return null;
    }

    @Override
    public List<FileData> upload(MultiValueMap<String, MultipartFile> fileMap, PreHandler preHandler) {
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

    private List<FileData> handleMultipartFiles(MultiValueMap<String, MultipartFile> fileMap) {
        long singleFileLimit = getSingleFileLimit();
        List<FileData> fileList = new ArrayList<>();
        fileMap.keySet().forEach(key -> fileMap.get(key).forEach(file -> {
                    if (!file.isEmpty()) {

                    }
        }));
    }

    /**
     * 初始化存储文件夹
     * @param fileDataList 文件列表
     * @param singleFileLimit 单个文件的最大值
     * @param file 上传的文件
     */
    private void handleFile(List<FileData> fileDataList, long singleFileLimit, MultipartFile file) {
        byte[] bytes = getFileBytes(file);
        String[] include = getFileConfiguration().getInclude();
        String[] exclude = getFileConfiguration().getExclude();
        // 获取文件的后缀名
        String ext = checkOneFile(include, exclude, singleFileLimit, file.getOriginalFilename(), bytes.length);
        // 生成新的文件名
        String newFileName = getNewFilename(ext);
        // 获取文件保存路径并生成文件夹
        String storePath = getStorePath(newFileName);
        // 生成文件的md5值
        String md5 = FileUtil.getFileMD5(bytes);
        FileData fileData = FileData.builder().
                name(newFileName).
                md5(md5).
                key(file.getName()).
                path(storePath).
                size(bytes.length).
                type(getFileType()).
                extension(ext).
                build();
        // 如果预处理器不为空，且处理结果为false，直接返回, 否则处理
        if (preHandler != null && !preHandler.handle(fileData)) {
            return;
        }
        boolean ok = han
    }

    // 获取单个上传文件的大小
    private long getSingleFileLimit() {
        String singleLimit = getFileConfiguration().getSingleLimit();
        return FileUtil.parseSize(singleLimit);
    }

    /**
     * 获取上传文件的字节数
     * @param file 上传文件
     * @return 上传文件的字节数
     */
    private byte[] getFileBytes(MultipartFile file) {
        byte[] bytes;
        try {
            bytes = file.getBytes();
        } catch (Exception e) {
            throw new FailedException(10190, "read file date failed");
        }
        return bytes;
    }

    /**
     * 校验单个文件
     * @param include 支持的类型
     * @param exclude 不支持的类型
     * @param singleFileLimit 单个文件大小限制
     * @param originName 文件原始名称
     * @param length 文件大小
     * @return 文件的扩展名，例如： .jpg
     */
    private String checkOneFile(String[] include, String[] exclude, long singleFileLimit, String originName, int length) {
        String ext = FileUtil.getFileExt(originName);
        // 检测后缀名
        if (!this.checkExt(include, exclude, ext)) {
            throw new FileExtensionException(ext + "文件类型不支持");
        }
        // 检测单个文件的大小
        if (length > singleFileLimit) {
            throw new FileTooLargeException(originName + "文件不能超过" + singleFileLimit);
        }
        return ext;
    }

    /**
     * 检查文件的后缀名是否在include或者exclude
     * @param include 支持的类型
     * @param exclude 不支持的类型
     * @param ext 后缀名
     * @return 是否通过
     */
    private boolean checkExt(String[] include, String[] exclude, String ext) {
        int inLen = include == null ? 0 : include.length;
        int exLen = exclude == null ? 0 : exclude.length;
        // 如果两者都有，取include
        if (inLen > 0 && exLen > 0) {
            return this.findInInclude(include, ext);
        } else if (inLen > 0) {
            // 有include，无exclude
            return this.findInInclude(include, ext);
        } else if (exLen > 0) {
            // 有exclude，无include
            return !this.findInInclude(exclude, ext);
        } else {
            // 二者都没有
            return true;
        }
    }

    private boolean findInInclude(String[] include, String ext) {
        for (String s : include) {
            if (s.equals(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 生成新的文件名
     * @param ext 文件后缀名
     * @return 新的文件名
     */
    private String getNewFilename(String ext) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid + ext;
    }
}
