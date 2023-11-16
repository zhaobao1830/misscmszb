package com.zb.misscmszb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zb.misscmszb.mapper.PermissionMapper;
import com.zb.misscmszb.model.PermissionDO;
import com.zb.misscmszb.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  权限服务实现类
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionDO> implements PermissionService {

    /**
     * 通过分组id得到分组的权限
     *
     * @param groupIds 分组id
     * @return 权限
     */
    @Override
    public List<PermissionDO> getPermissionByGroupIds(List<Integer> groupIds) {
        return this.baseMapper.selectPermissionsByGroupIds(groupIds);
    }
}
