package com.zb.misscmszb.controller.cms;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zb.misscmszb.core.annotation.GroupRequired;
import com.zb.misscmszb.core.annotation.PermissionMeta;
import com.zb.misscmszb.core.annotation.PermissionModule;
import com.zb.misscmszb.core.util.PageUtil;
import com.zb.misscmszb.dto.log.QueryLogDTO;
import com.zb.misscmszb.dto.query.BasePageDTO;
import com.zb.misscmszb.model.LogDO;
import com.zb.misscmszb.service.LogService;
import com.zb.misscmszb.vo.PageResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志控制器
 */
@RestController
@RequestMapping("/cms/log")
@PermissionModule(value = "日志")
@Validated
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/getLogs")
    @GroupRequired
    @PermissionMeta(value = "查询所有日志")
    public PageResponseVO<LogDO> getLogs(QueryLogDTO dto) {
        IPage<LogDO> iPage = logService.getLogPage(
                dto.getPage(), dto.getCount(),
                dto.getName(), dto.getStart(),
                dto.getEnd()
        );
        return PageUtil.build(iPage);
    }

    @GetMapping("/search")
    @GroupRequired
    @PermissionMeta(value = "搜索日志")
    public PageResponseVO<LogDO> searchLogs(QueryLogDTO dto) {
        IPage<LogDO> iPage = logService.searchLogPage(
                dto.getPage(), dto.getCount(),
                dto.getName(), dto.getKeyword(),
                dto.getStart(), dto.getEnd()
        );
        return PageUtil.build(iPage);
    }

    @GetMapping("/users")
    @GroupRequired
    @PermissionMeta(value = "查询日志记录的用户")
    public PageResponseVO<String> getUsers(@Validated BasePageDTO dto) {
        IPage<String> iPage = logService.getUserNamePage(dto.getPage(), dto.getCount());
        return PageUtil.build(iPage);
    }
}
