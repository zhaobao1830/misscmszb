package com.zb.misscmszb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zb.misscmszb.core.enumeration.GroupLevelEnum;
import com.zb.misscmszb.mapper.GroupMapper;
import com.zb.misscmszb.mapper.UserGroupMapper;
import com.zb.misscmszb.model.GroupDO;
import com.zb.misscmszb.model.UserGroupDO;
import com.zb.misscmszb.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 分组服务实现类
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {

    @Autowired
    private UserGroupMapper userGroupMapper;

    /**
     * 获得用户的所有分组id
     *
     * @param userId 用户id
     * @return 所有分组id
     */
    @Override
    public List<Integer> getUserGroupIdsByUserId(Integer userId) {
        return this.baseMapper.selectUserGroupIds(userId);
    }

    /**
     * 检查该用户是否在root分组中
     *
     * @param userId 用户id
     * @return true表示在
     */
    @Override
    public boolean checkIsRootByUserId(Integer userId) {
        QueryWrapper<UserGroupDO> wrapper = new QueryWrapper<>();
        Integer rootGroupId = this.getParticularGroupIdByLevel(GroupLevelEnum.ROOT);
        wrapper.lambda().eq(UserGroupDO::getUserId, userId)
                .eq(UserGroupDO::getGroupId, rootGroupId);
        UserGroupDO relation = userGroupMapper.selectOne(wrapper);
        return relation != null;
    }

    /**
     * 通过分组级别获取超级管理员分组或游客分组
     *
     * @param level GroupLevelEnum 枚举类
     * @return 用户组
     */
    @Override
    public GroupDO getParticularGroupByLevel(GroupLevelEnum level) {
        if (GroupLevelEnum.USER == level) {
            return null;
        } else {
            QueryWrapper<GroupDO> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(GroupDO::getLevel, level);
            return this.baseMapper.selectOne(wrapper);
        }
    }

    /**
     * 通过分组级别获取超级管理员分组或游客分组的id
     *
     * @param level GroupLevelEnum 枚举类
     * @return 用户组id
     */
    @Override
    public Integer getParticularGroupIdByLevel(GroupLevelEnum level) {
        GroupDO groupDO = this.getParticularGroupByLevel(level);
        return groupDO == null ? 0 : groupDO.getId();
    }

    /**
     * 获得用户的所有分组
     *
     * @param userId 用户id
     * @return 所有分组
     */
    @Override
    public List<GroupDO> getUserGroupsByUserId(Integer userId) {
        return this.baseMapper.selectGroupsByUserId(userId);
    }

    /**
     * 通过id检查分组是否存在
     *
     * @param id 分组id
     * @return 是否存在
     */
    @Override
    public boolean checkGroupExistById(Integer id) {
        return this.baseMapper.selectCountById(id) > 0;
    }

    /**
     * 获得分组下所有用户的id
     *
     * @param id 分组id
     * @return 用户id
     */
    @Override
    public List<Integer> getGroupUserIds(Integer id) {
        QueryWrapper<UserGroupDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserGroupDO::getGroupId, id);
        List<UserGroupDO> list = userGroupMapper.selectList(wrapper);
        return list.stream().map(UserGroupDO::getGroupId).collect(Collectors.toList());
    }
}
