package com.zb.misscmszb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zb.misscmszb.model.GroupPermissionDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分组权限mapper接口
 */
@Repository
public interface GroupPermissionMapper extends BaseMapper<GroupPermissionDO> {

    /**
     * 批量新增
     * @param relations 分组权限关系集合
     * @return last insert id
     */
    int insertBatch(@Param("relations") List<GroupPermissionDO> relations);

    /**
     * 根据分组id和权限id集合批量删除分组权限关系
     * @param groupId 分组id
     * @param permissionIds 权限id集合
     * @return 受影响行数
     */
    int deleteBatchByGroupIdAndPermissionId(@Param("groupId") Integer groupId, @Param("permissionIds") List<Integer> permissionIds);
}
