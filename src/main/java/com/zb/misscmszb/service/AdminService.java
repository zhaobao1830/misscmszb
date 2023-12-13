package com.zb.misscmszb.service;

import com.zb.misscmszb.dto.admin.DispatchPermissionsDTO;
import com.zb.misscmszb.dto.admin.NewGroupDTO;
import com.zb.misscmszb.dto.admin.RemovePermissionsDTO;
import com.zb.misscmszb.dto.admin.UpdateGroupDTO;
import com.zb.misscmszb.model.GroupDO;
import com.zb.misscmszb.model.PermissionDO;
import com.zb.misscmszb.vo.GroupPermissionVo;

import java.util.List;
import java.util.Map;

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

    /**
     * 获得所有权限信息
     */
    List<PermissionDO> getAllPermissions();

    /**
     * 获得结构化的权限信息
     */
    Map<String, List<PermissionDO>> getAllStructuralPermissions();

    /**
     * 获得分组数据
     *
     * @param id 分组id
     * @return 分组数据
     */
    GroupPermissionVo getGroup(Integer id);

    /**
     * 新建分组
     *
     * @param dto 分组信息
     * @return 是否成功
     */
    boolean createGroup(NewGroupDTO dto);

    /**
     * 分配权限
     *
     * @param dto 数据
     * @return 是否成功
     */
    boolean dispatchPermissions(DispatchPermissionsDTO dto);

    /**
     * 删除权限
     *
     * @param dto 数据
     * @return 是否成功
     */
    boolean removePermissions(RemovePermissionsDTO dto);
}
