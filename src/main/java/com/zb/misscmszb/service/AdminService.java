package com.zb.misscmszb.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zb.misscmszb.dto.admin.*;
import com.zb.misscmszb.model.GroupDO;
import com.zb.misscmszb.model.PermissionDO;
import com.zb.misscmszb.model.UserDO;
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

    /**
     * 更新用户信息
     *
     * @param validator 数据信息
     * @return 是否成功
     */
    boolean updateUserInfo(UpdateUserInfoDTO validator);

    /**
     * 通过分组id分页获取用户数据
     *
     * @param groupId 分组id
     * @param count   当前页数目
     * @param page    当前分页
     * @return 用户数据
     */
    IPage<UserDO> getUserPageByGroupId(Integer groupId, Integer count, Integer page);

    /**
     * 修改用户密码（重置用户密码）
     * @param dto id和密码信息
     * @return 是否修改成功
     */
    boolean changeUserPassword(ResetPasswordDTO dto);

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 是否删除成功
     */
    boolean deleteUser(Integer id);
}
