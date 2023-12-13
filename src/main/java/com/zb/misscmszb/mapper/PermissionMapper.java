package com.zb.misscmszb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zb.misscmszb.model.PermissionDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper extends BaseMapper<PermissionDO> {

    /**
     * 通过分组ids得到所有分组下的权限
     *
     * @param groupIds 分组ids
     * @return 权限
     */
    List<PermissionDO> selectPermissionsByGroupIds(@Param("groupIds") List<Integer> groupIds);

    /**
     * 通过分组id得到所有分组下的权限
     *
     * @param groupId 分组id
     * @return 权限
     */
    List<PermissionDO> selectPermissionsByGroupId(@Param("groupId") Integer groupId);
}
