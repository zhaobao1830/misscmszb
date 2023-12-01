package com.zb.misscmszb.core.exception;

import com.zb.misscmszb.bean.Code;
import org.springframework.http.HttpStatus;

/**
 * 禁止操作异常
 */
public class ForbiddenException extends HttpException{

    protected int code = Code.FORBIDDEN.getCode();

    protected int httpStatusCode = HttpStatus.FORBIDDEN.value();


    public ForbiddenException() {
        super(Code.FORBIDDEN.getCode(), Code.FORBIDDEN.getDescription());
        super.ifDefaultMessage=true;
    }

    public ForbiddenException(int code) {
        super(code, Code.FORBIDDEN.getDescription());
        this.code = code;
        super.ifDefaultMessage=true;
    }

    public ForbiddenException(int code, String message) {
        super(code, message);
        this.code = code;
    }

    public ForbiddenException(String message) {
        super(message);
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
