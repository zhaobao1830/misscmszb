package com.zb.misscmszb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zb.misscmszb.core.enumeration.GroupLevelEnum;
import com.zb.misscmszb.model.GroupDO;
import com.zb.misscmszb.vo.GroupPermissionVo;

import java.util.List;

/**
 * 分组服务接口
 */
public interface GroupService extends IService<GroupDO> {

    /**
     * 获得用户的所有分组id
     *
     * @param userId 用户id
     * @return 所有分组id
     */
    List<Integer> getUserGroupIdsByUserId(Integer userId);

    /**
     * 检查该用户是否在root分组中
     *
     * @param userId 用户id
     * @return true表示在
     */
    boolean checkIsRootByUserId(Integer userId);

    /**
     * 通过分组级别获取超级管理员分组或游客分组
     *
     * @param level GroupLevelEnum 枚举类
     * @return 用户组
     */
    GroupDO getParticularGroupByLevel(GroupLevelEnum level);

    /**
     * 通过分组级别获取超级管理员分组或游客分组的id
     *
     * @param level GroupLevelEnum 枚举类
     * @return 用户组id
     */
    Integer getParticularGroupIdByLevel(GroupLevelEnum level);

    /**
     * 获得用户的所有分组
     *
     * @param userId 用户id
     * @return 所有分组
     */
    List<GroupDO> getUserGroupsByUserId(Integer userId);

    /**
     * 通过id检查分组是否存在
     *
     * @param id 分组id
     * @return 是否存在
     */
    boolean checkGroupExistById(Integer id);

    /**
     * 获得分组下所有用户的id
     *
     * @param id 分组id
     * @return 用户id
     */
    List<Integer> getGroupUserIds(Integer id);

    /**
     * 通过名称检查分组是否存在
     *
     * @param name 分组名
     * @return 是否存在
     */
    boolean checkGroupExistByName(String name);

    /**
     * 获得分组及其权限
     *
     * @param id 分组id
     * @return 分组及权限
     */
    GroupPermissionVo getGroupAndPermissions(Integer id);

    /**
     * 删除用户与分组直接的关联
     *
     * @param userId    用户id
     * @param deleteIds 分组id
     */
    boolean deleteUserGroupRelations(Integer userId, List<Integer> deleteIds);

    /**
     * 添加用户与分组直接的关联
     *
     * @param userId 用户id
     * @param addIds 分组id
     */
    boolean addUserGroupRelations(Integer userId, List<Integer> addIds);
}
