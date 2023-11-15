package com.zb.misscmszb.core.exception;

import com.zb.misscmszb.bean.Code;
import org.springframework.http.HttpStatus;

/**
 * 令牌无效异常
 */
public class TokenInvalidException extends HttpException{

    protected int code = Code.TOKEN_INVALID.getCode();

    protected int httpStatusCode = HttpStatus.UNAUTHORIZED.value();

    public TokenInvalidException() {
        super(Code.TOKEN_INVALID.getCode(), Code.TOKEN_INVALID.getDescription());
        super.ifDefaultMessage=true;
    }

    public TokenInvalidException(String message) {
        super(message);
    }

    public TokenInvalidException(int code) {
        super(code, Code.TOKEN_INVALID.getDescription());
        this.code = code;
        super.ifDefaultMessage=true;
    }

    public TokenInvalidException(int code, String message) {
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
