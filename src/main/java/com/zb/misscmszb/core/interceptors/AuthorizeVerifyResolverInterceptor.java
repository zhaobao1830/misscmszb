package com.zb.misscmszb.core.interceptors;

import com.zb.misscmszb.bean.MetaInfo;
import com.zb.misscmszb.core.interceptors.interfaces.AuthorizeVerifyResolver;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 鉴权实现类
 */
@Component
public class AuthorizeVerifyResolverInterceptor implements AuthorizeVerifyResolver {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String BEARER_PATTERN = "^Bearer$";

    /**
     * 处理 LoginRequired的情况
     *
     * @param request  请求
     * @param response 响应
     * @param meta     路由信息
     * @return 是否成功
     */
    @Override
    public boolean handleLogin(HttpServletRequest request, HttpServletResponse response, MetaInfo meta) {
        return false;
    }

    /**
     * 处理 GroupRequired的情况
     *
     * @param request  请求
     * @param response 响应
     * @param meta     路由信息
     * @return 是否成功
     */
    @Override
    public boolean handleGroup(HttpServletRequest request, HttpServletResponse response, MetaInfo meta) {
        return false;
    }

    /**
     * 处理 AdminRequired的情况
     *
     * @param request  请求
     * @param response 响应
     * @param meta     路由信息
     * @return 是否成功
     */
    @Override
    public boolean handleAdmin(HttpServletRequest request, HttpServletResponse response, MetaInfo meta) {
        return false;
    }

    /**
     * 处理 RefreshRequired的情况
     *
     * @param request  请求
     * @param response 响应
     * @param meta     路由信息
     * @return 是否成功
     */
    @Override
    public boolean handleRefresh(HttpServletRequest request, HttpServletResponse response, MetaInfo meta) {
        return false;
    }

    /**
     * 处理 当前的handler 不是 HandlerMethod 的情况
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理器
     * @return 是否成功
     */
    @Override
    public boolean handleNotHandlerMethod(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return false;
    }
}
