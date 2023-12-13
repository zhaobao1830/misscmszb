package com.zb.misscmszb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zb.misscmszb.model.PermissionDO;

import java.util.List;
import java.util.Map;

/**
 * 权限服务接口
 */
public interface PermissionService extends IService<PermissionDO> {

    /**
     * 通过分组id得到分组的权限
     *
     * @param groupIds 分组id
     * @return 权限
     */
    List<PermissionDO> getPermissionByGroupIds(List<Integer> groupIds);

    /**
     * 将权限结构化
     *
     * @param permissions 权限
     * @return 结构化的权限
     */
    List<Map<String, List<Map<String, String>>>> structuringPermissions(List<PermissionDO> permissions);

    /**
     * 通过分组id得到分组的权限
     *
     * @param groupId 分组id
     * @return 权限
     */
    List<PermissionDO> getPermissionByGroupId(Integer groupId);
}
