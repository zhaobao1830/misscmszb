package com.zb.misscmszb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zb.misscmszb.model.GroupDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMapper extends BaseMapper<GroupDO> {

    List<GroupDO> selectGroupsByUserId(@Param("userId") Integer userId);

    /**
     * 获得用户的所有分组id
     *
     * @param userId 用户id
     * @return 所有分组id
     */
    List<Integer> selectUserGroupIds(@Param("userId") Integer userId);

    /**
     * 通过id获得分组个数
     *
     * @param id id
     * @return 个数
     */
    int selectCountById(@Param("id") Integer id);
}
