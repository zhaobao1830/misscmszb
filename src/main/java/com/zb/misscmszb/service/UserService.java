package com.zb.misscmszb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zb.misscmszb.model.PermissionDO;
import com.zb.misscmszb.model.UserDO;

import java.util.List;
import java.util.Map;

/**
 * 用户服务实现类
 */
public interface UserService extends IService<UserDO> {

    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return 用户
     */
    UserDO getUserByUsername(String username);

    /**
     * 获得用户所有权限
     *
     * @param userId 用户id
     * @return 权限
     */
    List<PermissionDO> getUserPermissions(Integer userId);

    /**
     * 获得用户所有权限
     *
     * @param userId 用户id
     * @return 权限
     */
    List<Map<String, List<Map<String, String>>>> getStructuralUserPermissions(Integer userId);
}
