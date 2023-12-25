package com.zb.misscmszb.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zb.misscmszb.core.mybatis.LinPage;
import com.zb.misscmszb.mapper.LogMapper;
import com.zb.misscmszb.model.LogDO;
import com.zb.misscmszb.service.LogService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 日志服务实现类
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, LogDO> implements LogService {

    @Override
    public IPage<LogDO> searchLogPage(Integer page, Integer count, String name, String keyword, Date start, Date end) {
        LinPage<LogDO> pager = new LinPage<>(page, count);
        if (keyword != null) {
            return this.baseMapper.searchLogsByUsernameAndKeywordAndRange(pager, name, "%" + keyword + "%", start, end);
        } else {
            return this.baseMapper.findLogsByUsernameAndRange(pager, name, start, end);
        }
    }

    @Override
    public IPage<String> getUserNamePage(Integer page, Integer count) {
        LinPage<LogDO> pager = new LinPage<>(page, count);
        return this.baseMapper.getUserNames(pager);
    }

    @Override
    public boolean createLog(String message, String permission, Integer userId, String username, String method, String path, Integer status) {
        LogDO log = LogDO.builder()
                .message(message)
                .userId(userId)
                .username(username)
                .statusCode(status)
                .method(method)
                .path(path)
                .build();
        if (permission != null) {
            log.setPermission(permission);
        }
        return this.baseMapper.insert(log) > 0;
    }

    @Override
    public boolean save(LogDO entity) {
        return super.save(entity);
    }
}
