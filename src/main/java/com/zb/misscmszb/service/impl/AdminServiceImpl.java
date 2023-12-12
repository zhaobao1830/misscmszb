package com.zb.misscmszb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zb.misscmszb.core.enumeration.GroupLevelEnum;
import com.zb.misscmszb.core.exception.ForbiddenException;
import com.zb.misscmszb.core.exception.NotFoundException;
import com.zb.misscmszb.dto.admin.UpdateGroupDTO;
import com.zb.misscmszb.model.GroupDO;
import com.zb.misscmszb.service.AdminService;
import com.zb.misscmszb.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private GroupService groupService;

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
