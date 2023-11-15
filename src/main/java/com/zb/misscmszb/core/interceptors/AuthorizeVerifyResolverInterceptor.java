package com.zb.misscmszb.core.interceptors;

import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.Claim;
import com.zb.misscmszb.bean.MetaInfo;
import com.zb.misscmszb.core.exception.AuthorizationException;
import com.zb.misscmszb.core.exception.TokenInvalidException;
import com.zb.misscmszb.core.interceptors.interfaces.AuthorizeVerifyResolver;
import com.zb.misscmszb.core.local.LocalUser;
import com.zb.misscmszb.extension.token.DoubleJWT;
import com.zb.misscmszb.model.UserDO;
import com.zb.misscmszb.module.file.FileConfiguration;
import com.zb.misscmszb.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 鉴权实现类
 */
@Component
public class AuthorizeVerifyResolverInterceptor implements AuthorizeVerifyResolver {

    @Autowired
    private DoubleJWT jwt;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private FileConfiguration fileConfiguration;

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
        String tokenStr = verifyHeader(request);
        Map<String, Claim> claims;
        try {
            claims = jwt.decodeAccessToken(tokenStr);
        } catch (TokenExpiredException e) {
            throw new com.zb.misscmszb.core.exception.TokenExpiredException(10051);
        } catch (AlgorithmMismatchException | SignatureVerificationException | JWTDecodeException |
                InvalidClaimException e) {
            throw new TokenInvalidException(10041);
        }
        return getClaim(claims);
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
        handleLogin(request, response, meta);
        UserDO user = LocalUser.getLocalUser();
        if (verifyAdmin(user)) {
            return true;
        }
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

    /**
     * 校验header
     * @param request 请求
     * @return token
     */
    private String verifyHeader(HttpServletRequest request) {
        // 处理头部header,带有access_token的可以访问
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        if (authorization == null || Strings.isBlank(authorization)) {
            throw new AuthorizationException(10012);
        }
        String[] splits = authorization.split(" ");

        final int tokenSplitLen = 2;
        if (splits.length != tokenSplitLen) {
            throw new AuthorizationException(10013);
        }
        // Bearer 字段
        String scheme = splits[0];
        // token 字段
        String tokenStr = splits[1];
        if (!Pattern.matches(BEARER_PATTERN, scheme)) {
            throw new AuthorizationException(10013);
        }
        return tokenStr;
    }

    private boolean getClaim(Map<String, Claim> claims) {
        if (claims == null) {
            throw new TokenInvalidException(10041);
        }
        int identity = claims.get("identity").asInt();
        UserDO user = userService.getById(identity);
        String avatarUrl;
        final String protocolPrefix = "http";
        if (user.getAvatar() == null) {
            avatarUrl = null;
        } else if (user.getAvatar().startsWith(protocolPrefix)) {
            avatarUrl = user.getAvatar();
        } else {
            avatarUrl = fileConfiguration.getDomain() + fileConfiguration.getServePath().split("/")[0] + "/" + user.getAvatar();
        }
        user.setAvatar(avatarUrl);
        LocalUser.setLocalUser(user);
        return true;
    }

    /**
     * 检查用户是否为管理员
     *
     * @param user 用户
     */
    private boolean verifyAdmin(UserDO user) {
        return groupService.checkIsRootByUserId(user.getId());
    }
}
