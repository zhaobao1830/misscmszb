package com.zb.misscmszb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zb.misscmszb.model.UserGroupDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户分组mapper
 */
@Repository
public interface UserGroupMapper extends BaseMapper<UserGroupDO> {

    /**
     * 批量新增
     * @param relations 用户分组数据对象集合
     * @return last insert id
     */
    int insertBatch(@Param("relations") List<UserGroupDO> relations);

    /**
     * 根据用户id删除
     * @param userId 用户id
     * @return 受影响行数
     */
    int deleteByUserId(@Param("user_id") Integer userId);

}
