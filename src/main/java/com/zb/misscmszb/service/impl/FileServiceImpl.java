package com.zb.misscmszb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zb.misscmszb.bo.FileBO;
import com.zb.misscmszb.mapper.FileMapper;
import com.zb.misscmszb.model.FileDO;
import com.zb.misscmszb.module.file.*;
import com.zb.misscmszb.service.FileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileDO> implements FileService {

    @Autowired
    private Uploader uploader;

    @Autowired
    private FileConfiguration fileConfiguration;

    /**
     * 上传文件
     *
     * @param fileMap 文件map
     * @return 文件数据
     */
    /**
     * 为什么不做批量插入
     * 1. 文件上传的数量一般不多，3个左右
     * 2. 批量插入不能得到数据的id字段，不利于直接返回数据
     */
    @Override
    public List<FileBO> upload(MultiValueMap<String, MultipartFile> fileMap) {
        List<FileBO> fileBOList = new ArrayList<>();
//        uploader.upload(fileMap, fileData -> {
//            FileDO found = baseMapper.selectByMd5(fileData.getMd5());
//            // 数据库中不存在
//            if (found == null) {
//                FileDO fileDO = new FileDO();
//                BeanUtils.copyProperties(fileData, fileDO);
//                baseMapper.insert(fileDO);
//                fileBOList.add(transformDoToBo(fileDO, fileData.getKey()));
//                return true;
//            }
//            // 已存在，则直接转化返回
//            fileBOList.add(transformDoToBo(found, fileData.getKey()));
//            return false;
//        });
        uploader.upload(fileMap, new UploadHandler() {
            @Override
            public boolean preHandle(FileObj fileObj) {
                FileDO fileDO = baseMapper.selectByMd5(fileObj.getMd5());
                // 数据库中不存在，存储操作放在上传到本地或云上之后
                if (fileDO == null) {
                    return true;
                }
                // 已存在，则直接转化返回
                fileBOList.add(transformDoToBo(fileDO, fileObj.getKey()));
                return false;
            }

            @Override
            public void afterHandle(FileObj fileObj) {
                // 保存到数据库
                FileDO fileDO = new FileDO();
                BeanUtils.copyProperties(fileObj, fileDO);
                getBaseMapper().insert(fileDO);
                fileBOList.add(transformDoToBo(fileDO, fileObj.getKey()));
            }
        });
        return fileBOList;
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

    private FileBO transformDoToBo(FileDO fileDO, String key) {
        FileBO fileBO = new FileBO();
        BeanUtils.copyProperties(fileDO, fileBO);
        if (fileDO.getType().equals(FileConstant.LOCAL)) {
            String s = fileConfiguration.getServePath().split("/")[0];
            // replaceAll 是将 windows 平台下的 \ 替换为 /
            if(System.getProperties().getProperty("os.name").toUpperCase().contains("WINDOWS")){
                fileBO.setUrl(fileConfiguration.getDomain() + s + "/" + fileDO.getPath().replaceAll("\\\\","/"));
            }else {
                fileBO.setUrl(fileConfiguration.getDomain() + s + "/" + fileDO.getPath());
            }
        } else {
            fileBO.setUrl(fileDO.getPath());
        }
        fileBO.setKey(key);
        return fileBO;
    }
}
