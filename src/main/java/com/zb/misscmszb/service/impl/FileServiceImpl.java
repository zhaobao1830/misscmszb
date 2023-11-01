package com.zb.misscmszb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zb.misscmszb.bo.FileBO;
import com.zb.misscmszb.mapper.FileMapper;
import com.zb.misscmszb.model.FileDO;
import com.zb.misscmszb.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileDO> implements FileService {
    /**
     * 上传文件
     *
     * @param fileMap 文件map
     * @return 文件数据
     */
    @Override
    public List<FileBO> upload(MultiValueMap<String, MultipartFile> fileMap) {
        return null;
    }

    /**
     * 通过md5检查文件是否存在
     *
     * @param md5 md5
     * @return true 表示已存在
     */
    @Override
    public boolean checkFileExistByMd5(String md5) {
        return this.baseMapper.selectCountByMd5(md5) > 0;
    }
}
