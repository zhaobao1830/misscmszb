package com.zb.misscmszb.core.exception;

import com.zb.misscmszb.bean.Code;
import org.springframework.http.HttpStatus;

/**
 * 认证异常
 */
public class AuthorizationException extends HttpException{

    protected int httpStatusCode = HttpStatus.UNAUTHORIZED.value();

    protected int code = Code.UN_AUTHORIZATION.getCode();

    public AuthorizationException() {
        super(Code.UN_AUTHORIZATION.getCode(), Code.UN_AUTHORIZATION.getDescription());
        super.ifDefaultMessage=true;
    }

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(int code) {
        super(code, Code.UN_AUTHORIZATION.getDescription());
        this.code = code;
        super.ifDefaultMessage=true;
    }

    public AuthorizationException(int code, String message) {
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
