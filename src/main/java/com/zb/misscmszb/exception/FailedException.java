package com.zb.misscmszb.exception;

import com.zb.misscmszb.bean.Code;
import org.springframework.http.HttpStatus;

/**
 * 失败异常
 */
public class FailedException extends HttpException{

    protected int code = Code.FAIL.getCode();

    protected int httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

    public FailedException() {
        super(Code.FAIL.getCode(), Code.FAIL.getDescription());
        super.ifDefaultMessage=true;
    }

    public FailedException(String message) {
        super(message);
    }

    public FailedException(int code) {
        super(code, Code.FAIL.getDescription());
        this.code = code;
        super.ifDefaultMessage=true;
    }

    public FailedException(int code, String message) {
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
