package com.zb.misscmszb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zb.misscmszb.mapper.PermissionMapper;
import com.zb.misscmszb.model.PermissionDO;
import com.zb.misscmszb.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 将权限结构化
     *
     * @param permissions 权限
     * @return 结构化的权限
     */
    @Override
    public List<Map<String, List<Map<String, String>>>> structuringPermissions(List<PermissionDO> permissions) {
        Map<String, List<Map<String, String>>> tmp = new HashMap<>(50);
        permissions.forEach(permission -> {
            if (!tmp.containsKey(permission.getModule())) {
                Map<String, String> tiny = new HashMap();
                tiny.put("module", permission.getModule());
                tiny.put("permission", permission.getName());
                List<Map<String, String>> mini = new ArrayList();
                mini.add(tiny);
                tmp.put(permission.getModule(), mini);
            } else {
                Map<String, String> tiny = new HashMap();
                tiny.put("module", permission.getModule());
                tiny.put("permission", permission.getName());
                tmp.get(permission.getModule()).add(tiny);
            }
        });
        List<Map<String, List<Map<String, String>>>> structualPermissions = new ArrayList();
        tmp.forEach((k, v) -> {
            Map<String, List<Map<String, String>>> ttmp = new HashMap();
            ttmp.put(k, v);
            structualPermissions.add(ttmp);
        });
        return structualPermissions;
    }
}
