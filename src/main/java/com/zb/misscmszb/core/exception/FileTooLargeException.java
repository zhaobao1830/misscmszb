package com.zb.misscmszb.core.exception;

import com.zb.misscmszb.bean.Code;
import org.springframework.http.HttpStatus;

public class FileTooLargeException extends HttpException{
    protected int code = Code.FILE_TOO_LARGE.getCode();

    protected int httpStatusCode = HttpStatus.PAYLOAD_TOO_LARGE.value();

    public FileTooLargeException() {
        super(Code.FILE_TOO_LARGE.getCode(), Code.FILE_TOO_LARGE.getDescription());
        super.ifDefaultMessage=true;
    }

    public FileTooLargeException(String message) {
        super(message);
    }

    public FileTooLargeException(int code) {
        super(code, Code.FILE_TOO_LARGE.getDescription());
        this.code = code;
        super.ifDefaultMessage=true;
    }

    public FileTooLargeException(int code, String message) {
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
