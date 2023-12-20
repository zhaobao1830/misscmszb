package com.zb.misscmszb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zb.misscmszb.core.enumeration.GroupLevelEnum;
import com.zb.misscmszb.core.exception.FailedException;
import com.zb.misscmszb.core.exception.ForbiddenException;
import com.zb.misscmszb.core.exception.NotFoundException;
import com.zb.misscmszb.core.exception.ParameterException;
import com.zb.misscmszb.core.local.LocalUser;
import com.zb.misscmszb.core.mybatis.LinPage;
import com.zb.misscmszb.core.util.BeanCopyUtil;
import com.zb.misscmszb.dto.user.ChangePasswordDTO;
import com.zb.misscmszb.dto.user.RegisterDTO;
import com.zb.misscmszb.dto.user.UpdateInfoDTO;
import com.zb.misscmszb.mapper.UserGroupMapper;
import com.zb.misscmszb.mapper.UserMapper;
import com.zb.misscmszb.model.PermissionDO;
import com.zb.misscmszb.model.UserDO;
import com.zb.misscmszb.model.UserGroupDO;
import com.zb.misscmszb.service.GroupService;
import com.zb.misscmszb.service.PermissionService;
import com.zb.misscmszb.service.UserIdentityService;
import com.zb.misscmszb.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private UserGroupMapper userGroupMapper;

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
     * 根据用户名检查用户是否存在
     *
     * @param email 邮箱
     * @return true代表存在
     */
    @Override
    public boolean checkUserExistByEmail(String email) {
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserDO::getEmail, email);
        Long rows = this.baseMapper.selectCount(wrapper);
        return rows > 0;
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

    /**
     * 新建用户
     *
     * @param validator 新建用户校验器
     * @return 被创建的用户
     */
    @Transactional
    @Override
    public UserDO createUser(RegisterDTO validator) {
        boolean exist = this.checkUserExistByUsername(validator.getUsername());
        if (exist) {
            throw new ForbiddenException(10071);
        }
        if (StringUtils.hasText(validator.getEmail())) {
            exist = this.checkUserExistByEmail(validator.getEmail());
            if (exist) {
                throw new ForbiddenException(10076);
            }
        } else {
            // bug 前端如果传入的email为 "" 时，由于数据库中存在""的email，会报duplication错误
            // 所以如果email为blank，必须显示设置为 null
            validator.setEmail(null);
        }
        UserDO user = new UserDO();
        BeanUtils.copyProperties(validator, user);
        this.baseMapper.insert(user);
        if (validator.getGroupIds() != null && !validator.getGroupIds().isEmpty()) {
            checkGroupsValid(validator.getGroupIds());
            checkGroupsExist(validator.getGroupIds());
            List<UserGroupDO> relations = validator.getGroupIds()
                    .stream()
                    .map(groupId -> new UserGroupDO(user.getId(), groupId))
                    .collect(Collectors.toList());
            userGroupMapper.insertBatch(relations);
        } else {
            // id为2的分组为游客分组
            Integer guestGroupId = groupService.getParticularGroupIdByLevel(GroupLevelEnum.GUEST);
            UserGroupDO relation = new UserGroupDO(user.getId(), guestGroupId);
            userGroupMapper.insert(relation);
        }
        userIdentityService.createUsernamePasswordIdentity(user.getId(), validator.getUsername(), validator.getPassword());
        return user;
    }

    /**
     * 获取超级管理员的id
     *
     * @return 超级管理员的id
     */
    @Override
    public Integer getRootUserId() {
        Integer rootGroupId = groupService.getParticularGroupIdByLevel(GroupLevelEnum.ROOT);
        UserGroupDO userGroupDO = null;
        if (rootGroupId != 0) {
            QueryWrapper<UserGroupDO> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(UserGroupDO::getGroupId, rootGroupId);
            userGroupDO = userGroupMapper.selectOne(wrapper);
        }
        return userGroupDO == null ? 0 : userGroupDO.getUserId();
    }

    /**
     * 根据分组id分页获取用户数据
     *
     * @param pager   分页
     * @param groupId 分组id
     * @return 数据页
     */
    @Override
    public IPage<UserDO> getUserPageByGroupId(LinPage<UserDO> pager, Integer groupId) {
        Integer rootGroupId = groupService.getParticularGroupIdByLevel(GroupLevelEnum.ROOT);
        return this.baseMapper.selectPageByGroupId(pager, groupId, rootGroupId);
    }

    /**
     * 根据用户id检查用户是否存在
     *
     * @param id 用户名
     * @return true代表存在
     */
    @Override
    public boolean checkUserExistById(Integer id) {
        int rows = this.baseMapper.selectCountById(id);
        return rows > 0;
    }

    private void checkGroupsValid(List<Integer> ids) {
        Integer rootGroupId = groupService.getParticularGroupIdByLevel(GroupLevelEnum.ROOT);
        boolean anyMatch = ids.stream().anyMatch(it -> it.equals(rootGroupId));
        if (anyMatch) {
            throw new ForbiddenException(10073);
        }
    }

    private void checkGroupsExist(List<Integer> ids) {
        for (Integer id : ids) {
            if (!groupService.checkGroupExistById(id)) {
                throw new NotFoundException(10023);
            }
        }
    }
}
