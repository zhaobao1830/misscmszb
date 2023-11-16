package com.zb.misscmszb.core.interceptors;

import com.zb.misscmszb.bean.MetaInfo;
import com.zb.misscmszb.bean.PermissionMetaCollector;
import com.zb.misscmszb.core.enumeration.UserLevel;
import com.zb.misscmszb.core.interceptors.interfaces.AuthorizeVerifyResolver;
import com.zb.misscmszb.core.util.AnnotationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 权限拦截器
 */
public class AuthorizeInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthorizeVerifyResolver authorizeVerifyResolver;

    @Autowired
    private PermissionMetaCollector permissionMetaCollector;

    private String[] excludeMethods = new String[]{"OPTIONS"};

    /**
     * 前置处理
     *
     * @param request  request 请求
     * @param response 相应
     * @param handler  处理器
     * @return 是否成功
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (checkInExclude(request.getMethod())) {
            // 有些请求方法无需检测，如OPTIONS
            return true;
        }
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            String methodName = method.getName();
            String className = method.getDeclaringClass().getName();
            String identity = className + "#" + methodName;
            MetaInfo meta = permissionMetaCollector.findMeta(identity);
            // 考虑两种情况，1. 有 meta；2. 无 meta
            if (meta == null) {
                // 无meta的话，adminRequired和loginRequired
                return this.handleNoMeta(request, response, method);
            } else {
                // 有meta在权限范围之内，需要判断权限
                return this.handleMeta(request, response, method, meta);
            }
        } else {
            // handler不是HandlerMethod的情况
            return authorizeVerifyResolver.handleNotHandlerMethod(request, response, handler);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        authorizeVerifyResolver.handlePostHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        authorizeVerifyResolver.handleAfterCompletion(request, response, handler, ex);
    }

    /**
     * 校验方法是否包含在数组里
     * @param method 待校验的方法
     * @return true/false
     */
    private boolean checkInExclude(String method) {
        for (String excludeMethod : excludeMethods) {
            if (method.equals(excludeMethod)) {
                return true;
            }
        }
        return false;
    }

    private boolean handleNoMeta(HttpServletRequest request, HttpServletResponse response, Method method) {
        Annotation[] annotations = method.getAnnotations();
        UserLevel level = AnnotationUtil.findRequired(annotations);
        switch (level) {
            case LOGIN:
            case GROUP:
                // 分组权限
                // 登陆权限
                return authorizeVerifyResolver.handleLogin(request, response, null);
            case ADMIN:
                // 管理员权限
                return authorizeVerifyResolver.handleAdmin(request, response, null);
            case REFRESH:
                // 刷新令牌
                return authorizeVerifyResolver.handleRefresh(request, response, null);
            default:
                return true;
        }
    }

    private boolean handleMeta(HttpServletRequest request, HttpServletResponse response, Method method, MetaInfo meta) {
        UserLevel level = meta.getUserLevel();
        switch (level) {
            case LOGIN:
                // 登陆权限
                return authorizeVerifyResolver.handleLogin(request, response, meta);
            case GROUP:
                // 分组权限
                return authorizeVerifyResolver.handleGroup(request, response, meta);
            case ADMIN:
                // 管理员权限
                return authorizeVerifyResolver.handleAdmin(request, response, meta);
            case REFRESH:
                // 刷新令牌
                return authorizeVerifyResolver.handleRefresh(request, response, meta);
            default:
                return true;
        }
    }
}
