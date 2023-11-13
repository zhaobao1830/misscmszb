package com.zb.misscmszb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zb.misscmszb.model.UserDO;

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
}
