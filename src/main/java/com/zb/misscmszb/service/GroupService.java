package com.zb.misscmszb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zb.misscmszb.core.enumeration.GroupLevelEnum;
import com.zb.misscmszb.model.GroupDO;

import java.util.List;

/**
 * 分组服务接口
 */
public interface GroupService extends IService<GroupDO> {

    /**
     * 获得用户的所有分组id
     *
     * @param userId 用户id
     * @return 所有分组id
     */
    List<Integer> getUserGroupIdsByUserId(Integer userId);

    /**
     * 检查该用户是否在root分组中
     *
     * @param userId 用户id
     * @return true表示在
     */
    boolean checkIsRootByUserId(Integer userId);

    /**
     * 通过分组级别获取超级管理员分组或游客分组
     *
     * @param level GroupLevelEnum 枚举类
     * @return 用户组
     */
    GroupDO getParticularGroupByLevel(GroupLevelEnum level);

    /**
     * 通过分组级别获取超级管理员分组或游客分组的id
     *
     * @param level GroupLevelEnum 枚举类
     * @return 用户组id
     */
    Integer getParticularGroupIdByLevel(GroupLevelEnum level);
}