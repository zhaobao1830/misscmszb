package com.zb.misscmszb.extension.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zb.misscmszb.common.constant.TokenConstant;
import com.zb.misscmszb.common.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

/**
 * 支持双令牌模式
 * access、refresh token
 */
@Getter
@Setter
public class DoubleJWT {

    // access过期时间
    private long accessExpire;

    // refresh过期时间
    private long refreshExpire;

    private Algorithm algorithm;

    private JWTVerifier accessVerifier;

    private JWTVerifier refreshVerifier;

    private JWTCreator.Builder builder;

    /**
     * 传入加密算法，双token模式
     *
     * @param algorithm     加密算法
     * @param accessExpire  access_token过期时间
     * @param refreshExpire refresh_token过期时间
     */
    public DoubleJWT(Algorithm algorithm, long accessExpire, long refreshExpire) {
        this.algorithm = algorithm;
        this.accessExpire = accessExpire;
        this.refreshExpire = refreshExpire;
        this.initBuilderAndVerifier();
    }

    public void initBuilderAndVerifier() {
        accessVerifier = JWT.require(algorithm)
                .acceptExpiresAt(this.accessExpire)
                .build();
        refreshVerifier = JWT.require(algorithm)
                .acceptExpiresAt(this.refreshExpire)
                .build();
        builder = JWT.create();
    }

    // 生成token
    public String generateToken(String tokenType, long identity, String scope, long expire) {
        Date expireDate = DateUtil.getDurationDate(expire);
        return builder
                .withClaim("type", tokenType)
                .withClaim("identity", identity)
                .withClaim("scope", scope)
                .withExpiresAt(expireDate)
                .sign(algorithm);
    }

    // 生成token
    public String generateToken(String tokenType, String identity, String scope, long expire) {
        Date expireDate = DateUtil.getDurationDate(expire);
        return builder
                .withClaim("type", tokenType)
                .withClaim("identity", identity)
                .withClaim("scope", scope)
                .withExpiresAt(expireDate)
                .sign(algorithm);
    }

    /**
     * 解析AccessToken
     * @param token 令牌
     * @return 令牌的内容
     */
    public Map<String, Claim> decodeAccessToken(String token) {
        DecodedJWT jwt = accessVerifier.verify(token);
        checkTokenExpired(jwt.getExpiresAt());
        checkTokenType(jwt.getClaim("type").asString(), TokenConstant.ACCESS_TYPE);
        checkTokenScope(jwt.getClaim("scope").asString());
        return jwt.getClaims();
    }

    /**
     * 解析RefreshToken
     * @param token 令牌
     * @return 令牌的内容
     */
    public Map<String, Claim> decodeRefreshToken(String token) {
        DecodedJWT jwt = refreshVerifier.verify(token);
        checkTokenExpired(jwt.getExpiresAt());
        checkTokenType(jwt.getClaim("type").asString(), TokenConstant.REFRESH_TYPE);
        checkTokenScope(jwt.getClaim("scope").asString());
        return jwt.getClaims();
    }

    /**
     * 校验token的到期时间
     * @param expiresAt 到期时间
     */
    private void checkTokenExpired(Date expiresAt) {
        long now = System.currentTimeMillis();
        if (expiresAt.getTime() < now) {
            throw new TokenExpiredException("token is expired", expiresAt.toInstant());
        }
    }

    /**
     * 校验令牌的类型
     * @param type 当前令牌的类型
     * @param constantType 自定义的令牌常量
     */
    private void checkTokenType(String type, String constantType) {
        if (type == null || !type.equals(constantType)) {
            throw new InvalidClaimException("token type is invalid");
        }
    }

    private void checkTokenScope(String scope) {
        if (scope == null || !scope.equals(TokenConstant.LIN_SCOPE)) {
            throw new InvalidClaimException("token scope is invalid");
        }
    }

    public String generateAccessToken(long identity) {
        return generateToken(TokenConstant.ACCESS_TYPE, identity, TokenConstant.LIN_SCOPE, this.accessExpire);
    }

    public String generateAccessToken(String identity) {
        return generateToken(TokenConstant.ACCESS_TYPE, identity, TokenConstant.LIN_SCOPE, this.accessExpire);
    }

    public String generateRefreshToken(long identity) {
        return generateToken(TokenConstant.REFRESH_TYPE, identity, TokenConstant.LIN_SCOPE, this.refreshExpire);
    }

    public String generateRefreshToken(String identity) {
        return generateToken(TokenConstant.REFRESH_TYPE, identity, TokenConstant.LIN_SCOPE, this.refreshExpire);
    }

    public Tokens generateTokens(String identity) {
        String access = this.generateToken(TokenConstant.ACCESS_TYPE, identity, TokenConstant.LIN_SCOPE, this.accessExpire);
        String refresh = this.generateToken(TokenConstant.REFRESH_TYPE, identity, TokenConstant.LIN_SCOPE, this.refreshExpire);
        return new Tokens(access, refresh);
    }

    public Long getAccessExpire() {
        return accessExpire;
    }


    public Long getRefreshExpire() {
        return refreshExpire;
    }
}
