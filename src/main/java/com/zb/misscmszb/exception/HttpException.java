package com.zb.misscmszb.exception;

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

    protected int code = Code
    /**
     * http 状态码
     *
     * @return http 状态码
     */
    @Override
    public int getHttpStatusCode() {
        return 0;
    }

    /**
     * 错误码
     *
     * @return 错误码
     */
    @Override
    public int getCode() {
        return 0;
    }
}
