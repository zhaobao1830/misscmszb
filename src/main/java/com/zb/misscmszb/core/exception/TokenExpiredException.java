package com.zb.misscmszb.core.exception;


import com.zb.misscmszb.bean.Code;
import org.springframework.http.HttpStatus;

/**
 * 令牌过期异常
 */
public class TokenExpiredException extends HttpException{

    protected int code = Code.TOKEN_EXPIRED.getCode();

    protected int httpStatusCode = HttpStatus.UNAUTHORIZED.value();

    public TokenExpiredException() {
        super(Code.TOKEN_EXPIRED.getCode(), Code.TOKEN_EXPIRED.getDescription());
        super.ifDefaultMessage=true;
    }

    public TokenExpiredException(String message) {
        super(message);
    }

    public TokenExpiredException(int code) {
        super(code, Code.TOKEN_EXPIRED.getDescription());
        this.code = code;
        super.ifDefaultMessage=true;
    }

    public TokenExpiredException(int code, String message) {
        super(code, message);
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public int getHttpStatusCode() {
        return httpStatusCode;
    }
}
