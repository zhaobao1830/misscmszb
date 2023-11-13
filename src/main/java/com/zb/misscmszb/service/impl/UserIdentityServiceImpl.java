package com.zb.misscmszb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zb.misscmszb.common.constant.IdentityConstant;
import com.zb.misscmszb.common.util.EncryptUtil;
import com.zb.misscmszb.mapper.UserIdentityMapper;
import com.zb.misscmszb.model.UserIdentityDO;
import com.zb.misscmszb.service.UserIdentityService;
import org.springframework.stereotype.Service;

@Service
public class UserIdentityServiceImpl extends ServiceImpl<UserIdentityMapper, UserIdentityDO> implements UserIdentityService {

    /**
     * 验证用户认证信息 (USERNAME_PASSWORD)
     *
     * @param userId   用户id
     * @param username 用户名
     * @param password 密码
     * @return 是否验证成功
     */
    @Override
    public boolean verifyUsernamePassword(Integer userId, String username, String password) {
        QueryWrapper<UserIdentityDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserIdentityDO::getUserId, userId)
                .eq(UserIdentityDO::getIdentityType, IdentityConstant.USERNAME_PASSWORD_IDENTITY)
                .eq(UserIdentityDO::getIdentifier, username);
        UserIdentityDO userIdentityDO = this.baseMapper.selectOne(wrapper);
        return EncryptUtil.verify(userIdentityDO.getCredential(), password);
    }
}
