package com.zb.misscmszb.service;

import com.zb.misscmszb.dto.admin.UpdateGroupDTO;
import com.zb.misscmszb.model.GroupDO;

import java.util.List;

/**
 * 管理员服务接口
 */
public interface AdminService {

    /**
     * 获得所有分组信息
     */
    List<GroupDO> getAllGroups();

    /**
     * 删除分组
     *
     * @param id 分组id
     * @return
     */
    boolean deleteGroup(Integer id);

    /**
     * 更新分组
     *
     * @param dto 分组信息
     */
    boolean updateGroup(UpdateGroupDTO dto);
}
