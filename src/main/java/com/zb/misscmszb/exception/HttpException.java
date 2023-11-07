package com.zb.misscmszb.exception;

import com.zb.misscmszb.bean.Code;
import com.zb.misscmszb.interfaces.BaseResponse;
import org.springframework.http.HttpStatus;

/**
 * HttpException 异常类
 * 异常信息 message
 * http状态码 httpStatusCode
 * 错误码 code
 */
public class HttpException extends RuntimeException implements BaseResponse {
    protected int httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

    protected int code = Code.INTERNAL_SERVER_ERROR.getCode();

    /**
     * 是否是默认消息
     * true： 没有通过构造函数传入 message
     * false：通过构造函数传入了 message
     *
     * 没有用 isDefaultMessage 是因为和部分 rpc 框架存在兼容问题
     */
    protected boolean ifDefaultMessage = true;

    public HttpException() {
        super(Code.INTERNAL_SERVER_ERROR.getDescription());
    }

    public HttpException(String message) {
        super(message);
        this.ifDefaultMessage = false;
    }

    public HttpException(int code) {
        super(Code.INTERNAL_SERVER_ERROR.getDescription());
        this.code = code;
    }

    public HttpException(int code, int httpStatusCode) {
        super(Code.INTERNAL_SERVER_ERROR.getDescription());
        this.httpStatusCode = httpStatusCode;
        this.code = code;
    }

    public HttpException(int code, String message) {
        super(message);
        this.code = code;
        this.ifDefaultMessage = false;
    }

    public HttpException(int code, String message, int httpStatusCode) {
        super(message);
        this.code = code;
        this.httpStatusCode = httpStatusCode;
        this.ifDefaultMessage = false;
    }

    public HttpException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public HttpException(Throwable cause, int code, int httpStatusCode) {
        super(cause);
        this.code = code;
        this.httpStatusCode = httpStatusCode;
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
        this.ifDefaultMessage = false;
    }

    /**
     * for better performance
     *
     * @return Throwable
     */
    public Throwable doFillInStackTrace() {
        return super.fillInStackTrace();
    }

    /**
     * http 状态码
     *
     * @return http 状态码
     */
    @Override
    public int getHttpStatusCode() {
        return this.httpStatusCode;
    }

    /**
     * 错误码
     *
     * @return 错误码
     */
    @Override
    public int getCode() {
        return this.code;
    }

    public boolean ifDefaultMessage() {
        return this.ifDefaultMessage;
    }
}
