package com.zb.misscmszb.core.exception;

import com.zb.misscmszb.bean.Code;
import org.springframework.http.HttpStatus;

public class FileTooManyException extends HttpException{

    protected int code = Code.FILE_TOO_MANY.getCode();

    protected int httpStatusCode = HttpStatus.PAYLOAD_TOO_LARGE.value();


    public FileTooManyException() {
        super(Code.FILE_TOO_MANY.getCode(), Code.FILE_TOO_MANY.getDescription());
        super.ifDefaultMessage=true;
    }

    public FileTooManyException(String message) {
        super(message);
    }

    public FileTooManyException(int code) {
        super(code, Code.FILE_TOO_MANY.getDescription());
        this.code = code;
        super.ifDefaultMessage=true;
    }

    public FileTooManyException(int code, String message) {
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
