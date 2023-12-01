package com.zb.misscmszb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zb.misscmszb.model.UserIdentityDO;

public interface UserIdentityService extends IService<UserIdentityDO> {

    /**
     * 验证用户认证信息 (USERNAME_PASSWORD)
     *
     * @param userId   用户id
     * @param username 用户名
     * @param password 密码
     * @return 是否验证成功
     */
    boolean verifyUsernamePassword(Integer userId, String username, String password);

    /**
     * 修改用户名
     *
     * @param userId   用户id
     * @param username 新用户名
     * @return 是否成功
     */
    boolean changeUsername(Integer userId, String username);
}
