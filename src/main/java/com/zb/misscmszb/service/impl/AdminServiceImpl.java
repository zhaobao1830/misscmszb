package com.zb.misscmszb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zb.misscmszb.core.enumeration.GroupLevelEnum;
import com.zb.misscmszb.core.exception.ForbiddenException;
import com.zb.misscmszb.core.exception.NotFoundException;
import com.zb.misscmszb.dto.admin.*;
import com.zb.misscmszb.mapper.GroupPermissionMapper;
import com.zb.misscmszb.model.GroupDO;
import com.zb.misscmszb.model.GroupPermissionDO;
import com.zb.misscmszb.model.PermissionDO;
import com.zb.misscmszb.service.AdminService;
import com.zb.misscmszb.service.GroupService;
import com.zb.misscmszb.service.PermissionService;
import com.zb.misscmszb.vo.GroupPermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private GroupService groupService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private GroupPermissionMapper groupPermissionMapper;

    /**
     * 获得所有分组信息
     */
    @Override
    public List<GroupDO> getAllGroups() {
        QueryWrapper<GroupDO> wrapper = new QueryWrapper<>();
        Integer rootGroupId = groupService.getParticularGroupIdByLevel(GroupLevelEnum.ROOT);
        wrapper.lambda().ne(GroupDO::getId, rootGroupId);
        return groupService.list(wrapper);
    }

    /**
     * 删除分组
     *
     * @param id 分组id
     * @return
     */
    @Override
    public boolean deleteGroup(Integer id) {
        Integer rootGroupId = groupService.getParticularGroupIdByLevel(GroupLevelEnum.ROOT);
        Integer guestGroupId = groupService.getParticularGroupIdByLevel(GroupLevelEnum.GUEST);
        if (id.equals(rootGroupId)) {
            throw new ForbiddenException(10074);
        }
        if (id.equals(guestGroupId)) {
            throw new ForbiddenException(10075);
        }
        throwGroupNotExistById(id);
        List<Integer> groupUserIds = groupService.getGroupUserIds(id);
        if(!groupUserIds.isEmpty()) {
            throw new ForbiddenException(10027);
        }
        return groupService.removeById(id);
    }

    /**
     * 更新分组
     *
     * @param dto 分组信息
     * @return
     */
    @Override
    public boolean updateGroup(UpdateGroupDTO dto) {
        GroupDO groupDO = groupService.getById(dto.getId());
        if (groupDO == null) {
            throw new NotFoundException(10024);
        }
        if (!groupDO.getName().equals(dto.getName())) {
            throwGroupNameExist(dto.getName());
        }
        GroupDO groupDO1 = GroupDO.builder().name(dto.getName()).info(dto.getInfo()).build();
        groupDO1.setId(dto.getId());
        return groupService.updateById(groupDO1);
    }

    /**
     * 获得所有权限信息
     */
    @Override
    public List<PermissionDO> getAllPermissions() {
        QueryWrapper<PermissionDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PermissionDO::getMount, true);
        return permissionService.list(wrapper);
    }

    /**
     * 获得结构化的权限信息
     */
    @Override
    public Map<String, List<PermissionDO>> getAllStructuralPermissions() {
        List<PermissionDO> permissions = getAllPermissions();
        Map<String, List<PermissionDO>> res = new HashMap<>();
        permissions.forEach(permission -> {
            if (res.containsKey(permission.getModule())) {
                res.get(permission.getModule()).add(permission);
            } else {
                ArrayList<PermissionDO> t = new ArrayList<>();
                t.add(permission);
                res.put(permission.getModule(), t);
            }
        });
        return res;
    }

    /**
     * 获得分组数据
     *
     * @param id 分组id
     * @return 分组数据
     */
    @Override
    public GroupPermissionVo getGroup(Integer id) {
        throwGroupNotExistById(id);
        return groupService.getGroupAndPermissions(id);
    }

    /**
     * 新建分组
     *
     * @param dto 分组信息
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean createGroup(NewGroupDTO dto) {
        throwGroupNameExist(dto.getName());
        GroupDO group = GroupDO.builder().name(dto.getName()).info(dto.getInfo()).build();
        groupService.save(group);
        if (dto.getPermissionIds() != null && !dto.getPermissionIds().isEmpty()) {
            List<GroupPermissionDO> relations = dto.getPermissionIds().stream()
                    .map(id -> new GroupPermissionDO(group.getId(), id))
                    .collect(Collectors.toList());
            groupPermissionMapper.insertBatch(relations);
        }
        return true;
    }

    /**
     * 分配权限
     *
     * @param dto 数据
     * @return 是否成功
     */
    @Override
    public boolean dispatchPermissions(DispatchPermissionsDTO dto) {
        List<GroupPermissionDO> relations = dto.getPermissionIds().stream()
                .map(id -> new GroupPermissionDO(dto.getGroupId(), id))
                .collect(Collectors.toList());
        return groupPermissionMapper.insertBatch(relations) > 0;
    }

    /**
     * 删除权限
     *
     * @param dto 数据
     * @return 是否成功
     */
    @Override
    public boolean removePermissions(RemovePermissionsDTO dto) {
        return groupPermissionMapper.deleteBatchByGroupIdAndPermissionId(dto.getGroupId(), dto.getPermissionIds()) > 0;
    }

    /**
     * 更新用户信息
     *
     * @param validator 数据信息
     * @return 是否成功
     */
    @Override
    public boolean updateUserInfo(UpdateUserInfoDTO validator) {
        List<Integer> newGroupIds = validator.getGroupIds();
        Integer rootGroupId = groupService.getParticularGroupIdByLevel(GroupLevelEnum.ROOT);
        boolean anyMatch = newGroupIds.stream().anyMatch(it -> it.equals(rootGroupId));
        if (anyMatch) {
            throw new ForbiddenException(10073);
        }
        List<Integer> existGroupIds = groupService.getUserGroupIdsByUserId(validator.getId());
        // 删除existGroupIds有，而newGroupIds没有的
        List<Integer> deleteIds = existGroupIds.stream().filter(it -> !newGroupIds.contains(it)).collect(Collectors.toList());
        // 添加newGroupIds有，而existGroupIds没有的
        List<Integer> addIds = newGroupIds.stream().filter(it -> !existGroupIds.contains(it)).collect(Collectors.toList());
        return groupService.deleteUserGroupRelations(validator.getId(), deleteIds) && groupService.addUserGroupRelations(validator.getId(), addIds);
    }

    private void throwGroupNotExistById(Integer id) {
        boolean exist = groupService.checkGroupExistById(id);
        if (!exist) {
            throw new NotFoundException(10024);
        }
    }

    private void throwGroupNameExist(String name) {
        boolean exist = groupService.checkGroupExistByName(name);
        if (exist) {
            throw new ForbiddenException(10072);
        }
    }
}
