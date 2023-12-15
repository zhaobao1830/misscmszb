package com.zb.misscmszb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zb.misscmszb.core.constant.IdentityConstant;
import com.zb.misscmszb.core.util.EncryptUtil;
import com.zb.misscmszb.mapper.UserIdentityMapper;
import com.zb.misscmszb.model.UserIdentityDO;
import com.zb.misscmszb.service.UserIdentityService;
import org.springframework.stereotype.Service;

@Service
public class UserIdentityServiceImpl extends ServiceImpl<UserIdentityMapper, UserIdentityDO> implements UserIdentityService {

    /**
     * 新建用户认证信息
     *
     * @param userId       用户id
     * @param identityType 认证类型
     * @param identifier   认证（用户名）
     * @param credential   凭证（密码）
     * @return 用户认证
     */
    @Override
    public UserIdentityDO createIdentity(Integer userId, String identityType, String identifier, String credential) {
        UserIdentityDO userIdentity = new UserIdentityDO();
        userIdentity.setUserId(userId);
        userIdentity.setIdentityType(identityType);
        userIdentity.setIdentifier(identifier);
        userIdentity.setCredential(credential);
        baseMapper.insert(userIdentity);
        return userIdentity;
    }

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

    /**
     * 修改用户名
     *
     * @param userId   用户id
     * @param username 新用户名
     * @return 是否成功
     */
    @Override
    public boolean changeUsername(Integer userId, String username) {
        UserIdentityDO userIdentity = UserIdentityDO.builder().identifier(username).build();
        QueryWrapper<UserIdentityDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserIdentityDO::getUserId, userId);
        return this.baseMapper.update(userIdentity, wrapper) > 0;
    }

    /**
     * 修改密码
     *
     * @param userId   用户id
     * @param password 新密码
     * @return 是否成功
     */
    @Override
    public boolean changePassword(Integer userId, String password) {
        String encrypted = EncryptUtil.encrypt(password);
        UserIdentityDO userIdentity = UserIdentityDO.builder().credential(encrypted).build();
        QueryWrapper<UserIdentityDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserIdentityDO::getUserId, userId);
        return this.baseMapper.update(userIdentity, wrapper) > 0;
    }

    /**
     * 新建用户认证信息 (USERNAME_PASSWORD)
     *
     * @param userId   用户id
     * @param username 用户名
     * @param password 密码
     * @return 用户认证
     */
    @Override
    public UserIdentityDO createUsernamePasswordIdentity(Integer userId, String username, String password) {
        // 密码加密
        password = EncryptUtil.encrypt(password);
        return createIdentity(userId, IdentityConstant.USERNAME_PASSWORD_IDENTITY, username, password);
    }
}
