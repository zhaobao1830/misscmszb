package com.zb.misscmszb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zb.misscmszb.model.GroupDO;

/**
 * 分组服务接口
 */
public interface GroupService extends IService<GroupDO> {

    /**
     * 检查该用户是否在root分组中
     *
     * @param userId 用户id
     * @return true表示在
     */
    boolean checkIsRootByUserId(Integer userId);
}
