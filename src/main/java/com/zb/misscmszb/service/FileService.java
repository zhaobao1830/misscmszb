package com.zb.misscmszb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zb.misscmszb.bo.FileBO;
import com.zb.misscmszb.model.FileDO;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService extends IService<FileDO> {
    /**
     * 上传文件
     * @param fileMap 文件map
     * @return 文件数据
     */
    List<FileBO> upload(MultiValueMap<String, MultipartFile> fileMap);

    /**
     * 通过md5检查文件是否存在
     *
     * @param md5 md5
     * @return true 表示已存在
     */
    boolean checkFileExistByMd5(String md5);
}
