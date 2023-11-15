package com.zb.misscmszb.core.exception;

import com.zb.misscmszb.core.configuration.CodeMessageConfiguration;
import com.zb.misscmszb.core.util.RequestUtil;
import com.zb.misscmszb.vo.UnifyResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;

/**
 * 异常配置类
 *
 * 只要配置了这个类 自定义的异常才能正常返回值
 */
@Order
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler({HttpException.class})
    public UnifyResponseVO<String> processException(HttpException exception, HttpServletRequest request, HttpServletResponse response) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        UnifyResponseVO<String> unifyResponseVO = new UnifyResponseVO<>();
        unifyResponseVO.setRequest(RequestUtil.getSimpleRequest(request));
        int code = exception.getCode();
        unifyResponseVO.setCode(code);
        response.setStatus(exception.getHttpStatusCode());
        String errorMessage = CodeMessageConfiguration.getMessage(code);
        if (!StringUtils.hasText(errorMessage)) {
            unifyResponseVO.setMessage(exception.getMessage());
            log.error("", exception);
        } else {
            unifyResponseVO.setMessage(errorMessage);
            log.error(exception.getClass().getConstructor(int.class, String.class).newInstance(code, errorMessage).toString());
        }
        return unifyResponseVO;
    }
}
