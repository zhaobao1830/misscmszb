package com.zb.misscmszb.core.interceptors.interfaces;

import com.zb.misscmszb.core.annotation.Logger;
import com.zb.misscmszb.core.annotation.PermissionMeta;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 行为日志记录
 */
public interface LoggerResolver {

    /**
     * 处理
     *
     * @param meta     路由信息
     * @param logger   logger 信息
     * @param request  请求
     * @param response 响应
     */
    void handle(PermissionMeta meta, Logger logger, HttpServletRequest request, HttpServletResponse response);

}
