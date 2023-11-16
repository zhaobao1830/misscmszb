package com.zb.misscmszb.core.exception;

import com.zb.misscmszb.bean.Code;
import org.springframework.http.HttpStatus;

/**
 * 授权异常
 */
public class AuthenticationException extends HttpException{

    protected int httpStatusCode = HttpStatus.UNAUTHORIZED.value();

    protected int code = Code.UN_AUTHENTICATION.getCode();

    public AuthenticationException() {
        super(Code.UN_AUTHENTICATION.getCode(), Code.UN_AUTHENTICATION.getDescription());
        super.ifDefaultMessage = true;
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(int code) {
        super(code, Code.UN_AUTHENTICATION.getDescription());
        this.code = code;
        super.ifDefaultMessage = true;
    }

    public AuthenticationException(int code, String message) {
        super(code, message);
        this.code = code;
    }

    @Override
    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    @Override
    public int getCode() {
        return code;
    }
}
