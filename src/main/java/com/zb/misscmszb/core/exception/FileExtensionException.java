package com.zb.misscmszb.core.exception;

import com.zb.misscmszb.bean.Code;
import org.springframework.http.HttpStatus;

public class FileExtensionException extends HttpException{

    protected int code = Code.FILE_EXTENSION.getCode();

    protected int httpStatusCode = HttpStatus.NOT_ACCEPTABLE.value();

    public FileExtensionException() {
        super(Code.FILE_EXTENSION.getCode(), Code.FILE_EXTENSION.getDescription());
        super.ifDefaultMessage=true;
    }

    public FileExtensionException(String message) {
        super(message);
    }


    public FileExtensionException(int code) {
        super(code, Code.FILE_EXTENSION.getDescription());
        this.code = code;
        super.ifDefaultMessage=true;
    }

    public FileExtensionException(int code, String message) {
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
