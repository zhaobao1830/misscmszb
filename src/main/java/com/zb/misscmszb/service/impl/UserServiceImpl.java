package com.zb.misscmszb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zb.misscmszb.core.exception.FailedException;
import com.zb.misscmszb.core.exception.ForbiddenException;
import com.zb.misscmszb.core.exception.ParameterException;
import com.zb.misscmszb.core.local.LocalUser;
import com.zb.misscmszb.core.util.BeanCopyUtil;
import com.zb.misscmszb.dto.user.ChangePasswordDTO;
import com.zb.misscmszb.dto.user.UpdateInfoDTO;
import com.zb.misscmszb.mapper.UserMapper;
import com.zb.misscmszb.model.PermissionDO;
import com.zb.misscmszb.model.UserDO;
import com.zb.misscmszb.service.GroupService;
import com.zb.misscmszb.service.PermissionService;
import com.zb.misscmszb.service.UserIdentityService;
import com.zb.misscmszb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Autowired
    private UserIdentityService userIdentityService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    @Override
    public UserDO getUserByUsername(String username) {
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserDO::getUsername, username);
        return this.getOne(wrapper);
    }

    /**
     * 获得用户所有权限
     *
     * @param userId 用户id
     * @return 权限
     */
    @Override
    public List<PermissionDO> getUserPermissions(Integer userId) {
        // 查找用户搜索分组，查找分组下的所有权限
        List<Integer> groupIds = groupService.getUserGroupIdsByUserId(userId);
        if (groupIds == null || groupIds.isEmpty()) {
            return new ArrayList<>();
        }
        return permissionService.getPermissionByGroupIds(groupIds);
    }

    /**
     * 获得用户所有权限
     *
     * @param userId 用户id
     * @return 权限
     */
    @Override
    public List<Map<String, List<Map<String, String>>>> getStructuralUserPermissions(Integer userId) {
        List<PermissionDO> permissions = getUserPermissions(userId);
        return permissionService.structuringPermissions(permissions);
    }

    /**
     * 更新用户
     *
     * @param dto 更新用户信息用户校验器
     */
    @Transactional
    @Override
    public void updateUserInfo(UpdateInfoDTO dto) {
        UserDO user = LocalUser.getLocalUser();
        if (StringUtils.hasText(dto.getUsername())) {
            boolean exist = this.checkUserExistByUsername(dto.getUsername());
            if (exist) {
                throw new ForbiddenException(10071);
            }

            boolean changeSuccess = userIdentityService.changeUsername(user.getId(), dto.getUsername());
            if (changeSuccess) {
                user.setUsername(dto.getUsername());
            }
        }
        BeanCopyUtil.copyNonNullProperties(dto, user);

        this.baseMapper.updateById(user);
    }

    @Override
    public boolean checkUserExistByUsername(String username) {
        int rows = this.baseMapper.selectCountByUsername(username);
        return rows > 2;
    }

    /**
     * 修改用户密码
     *
     * @param dto 修改密码校验器
     */
    @Override
    public void changeUserPassword(ChangePasswordDTO dto) {
        UserDO user = LocalUser.getLocalUser();
        boolean valid = userIdentityService.verifyUsernamePassword(user.getId(), user.getUsername(), dto.getOldPassword());
        if (!valid) {
            throw new ParameterException(10032);
        }
        valid = userIdentityService.changePassword(user.getId(), dto.getNewPassword());
        if (!valid) {
            throw new FailedException(10011);
        }
    }
}
