package com.zb.misscmszb.core.exception;

import com.zb.misscmszb.bean.Code;
import com.zb.misscmszb.core.configuration.CodeMessageConfiguration;
import com.zb.misscmszb.vo.UnifyResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zb.misscmszb.core.util.RequestUtil.getSimpleRequest;

/**
 * 异常配置类
 * 只有配置了这个类 自定义的异常才能正常返回值
 */
@Order
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @Value("${spring.servlet.multipart.max-file-size:20M}")
    private String maxFileSize;

    /**
     * Exception
     */
    @ExceptionHandler({Exception.class})
    public UnifyResponseVO<String> processException(Exception exception,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response) {
        log.error("", exception);
        UnifyResponseVO<String> result = new UnifyResponseVO<>();
        result.setRequest(getSimpleRequest(request));
        result.setMessage(Code.INTERNAL_SERVER_ERROR.getZhDescription());
        result.setCode(Code.INTERNAL_SERVER_ERROR.getCode());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return result;
    }

    @ExceptionHandler({HttpException.class})
    public UnifyResponseVO<String> processException(HttpException exception, HttpServletRequest request, HttpServletResponse response) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        UnifyResponseVO<String> unifyResponseVO = new UnifyResponseVO<>();
        unifyResponseVO.setRequest(getSimpleRequest(request));
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

    /**
     * 将请求体解析并绑定到 java bean 时，如果出错，则抛出 MethodArgumentNotValidException 异常
     * params 绑定到 java bean 出错时抛出 BindException 异常
     * MethodArgumentNotValidException extends BindException，所以只要处理BindException
     */
    @ExceptionHandler({BindException.class})
    public UnifyResponseVO<Map<String, Object>> processException(BindException exception,
                                                                 HttpServletRequest request,
                                                                 HttpServletResponse response) {
        log.error(exception.toString());
        Map<String, Object> msg = new HashMap<>(3);
        exception.getAllErrors().forEach(error -> {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                msg.put(com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(fieldError.getField()),
                        fieldError.getDefaultMessage());
            } else {
                msg.put(com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(error.getObjectName()),
                        error.getDefaultMessage());
            }
        });
        return getMapUnifyResponseVO(request, response, msg);
    }

    /**
     * 普通参数(非 java bean)校验出错时抛出 ConstraintViolationException 异常
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public UnifyResponseVO<Map<String, Object>> processException(ConstraintViolationException exception,
                                                                 HttpServletRequest request,
                                                                 HttpServletResponse response) {
        log.error("", exception);
        Map<String, Object> msg = new HashMap<>(3);
        exception.getConstraintViolations().forEach(constraintViolation -> {
            String template = constraintViolation.getMessage();
            String path = constraintViolation.getPropertyPath().toString();
            msg.put(com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(path), template);
        });
        return getMapUnifyResponseVO(request, response, msg);
    }

    /**
     * NoHandlerFoundException 404
     */
    @ExceptionHandler({NoHandlerFoundException.class})
    public UnifyResponseVO<String> processException(NoHandlerFoundException exception,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response) {
        log.error("", exception);
        UnifyResponseVO<String> unifyResponse = new UnifyResponseVO<>();
        unifyResponse.setRequest(getSimpleRequest(request));
        String message = CodeMessageConfiguration.getMessage(10025);
        if (!StringUtils.hasText(message)) {
            unifyResponse.setMessage(exception.getMessage());
        } else {
            unifyResponse.setMessage(message);
        }
        unifyResponse.setCode(Code.NOT_FOUND.getCode());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return unifyResponse;
    }

    /**
     * MissingServletRequestParameterException 请求参数缺失
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public UnifyResponseVO<String> processException(MissingServletRequestParameterException exception,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response) {
        log.error("", exception);
        UnifyResponseVO<String> result = new UnifyResponseVO<>();
        result.setRequest(getSimpleRequest(request));

        String errorMessage = CodeMessageConfiguration.getMessage(10150);
        if (!StringUtils.hasText(errorMessage)) {
            result.setMessage(exception.getMessage());
        } else {
            result.setMessage(errorMessage + exception.getParameterName());
        }
        result.setCode(Code.PARAMETER_ERROR.getCode());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return result;
    }

    /**
     * MethodArgumentTypeMismatchException 参数类型不匹配
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public UnifyResponseVO<String> processException(MethodArgumentTypeMismatchException exception,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response) {
        log.error("", exception);
        UnifyResponseVO<String> result = new UnifyResponseVO<>();
        result.setRequest(getSimpleRequest(request));
        String errorMessage = CodeMessageConfiguration.getMessage(10160);
        if (!StringUtils.hasText(errorMessage)) {
            result.setMessage(exception.getMessage());
        } else {
            result.setMessage(exception.getValue() + errorMessage);
        }
        result.setCode(Code.PARAMETER_ERROR.getCode());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return result;
    }

    /**
     * ServletException
     */
    @ExceptionHandler({ServletException.class})
    public UnifyResponseVO<String> processException(ServletException exception,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response) {
        log.error("", exception);
        UnifyResponseVO<String> result = new UnifyResponseVO<>();
        result.setRequest(getSimpleRequest(request));
        result.setMessage(exception.getMessage());
        result.setCode(Code.FAIL.getCode());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return result;
    }

    /**
     * HttpMessageNotReadableException 处理HTTP请求时。 它表示无法读取HTTP请求消息的原始内容，可能是因为消息格式不正确、缺少必需的请求参数或请求体
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public UnifyResponseVO<String> processException(HttpMessageNotReadableException exception,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response) {
        log.error("", exception);
        UnifyResponseVO<String> result = new UnifyResponseVO<>();
        result.setRequest(getSimpleRequest(request));
        String errorMessage = CodeMessageConfiguration.getMessage(10170);
        Throwable cause = exception.getCause();
        if (cause != null) {
            String msg = convertMessage(cause);
            result.setMessage(msg);
        } else {
            if (!StringUtils.hasText(errorMessage)) {
                result.setMessage(exception.getMessage());
            } else {
                result.setMessage(errorMessage);
            }
        }
        result.setCode(Code.PARAMETER_ERROR.getCode());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return result;
    }

    /**
     * TypeMismatchException 类型转换错误
     */
    @ExceptionHandler({TypeMismatchException.class})
    public UnifyResponseVO<String> processException(TypeMismatchException exception,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response) {
        log.error("", exception);
        UnifyResponseVO<String> result = new UnifyResponseVO<>();
        result.setRequest(getSimpleRequest(request));
        result.setMessage(exception.getMessage());
        result.setCode(Code.PARAMETER_ERROR.getCode());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return result;
    }

    /**
     * MaxUploadSizeExceededException 上传的文件大小超过了服务器所允许的最大限制
     */
    @ExceptionHandler({MaxUploadSizeExceededException.class})
    public UnifyResponseVO<String> processException(MaxUploadSizeExceededException exception,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response) {
        log.error("", exception);
        UnifyResponseVO<String> result = new UnifyResponseVO<>();
        result.setRequest(getSimpleRequest(request));
        String errorMessage = CodeMessageConfiguration.getMessage(10180);
        if (!StringUtils.hasText(errorMessage)) {
            result.setMessage(exception.getMessage());
        } else {
            result.setMessage(errorMessage + maxFileSize);
        }
        result.setCode(Code.FILE_TOO_LARGE.getCode());
        response.setStatus(HttpStatus.PAYLOAD_TOO_LARGE.value());
        return result;
    }

    private UnifyResponseVO<Map<String, Object>> getMapUnifyResponseVO(HttpServletRequest request,
                                                                       HttpServletResponse response,
                                                                       Map<String, Object> msg) {
        UnifyResponseVO<Map<String, Object>> unifyResponse = new UnifyResponseVO<>();
        unifyResponse.setRequest(getSimpleRequest(request));
        unifyResponse.setMessage(msg);
        unifyResponse.setCode(Code.PARAMETER_ERROR.getCode());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return unifyResponse;
    }

    /**
     * 传参类型错误时，用于消息转换
     *
     * @param throwable 异常
     * @return 错误信息
     */
    private String convertMessage(Throwable throwable) {
        String error = throwable.toString();
        String regulation = "\\[\"(.*?)\"]+";
        Pattern pattern = Pattern.compile(regulation);
        Matcher matcher = pattern.matcher(error);
        String group = "";
        if (matcher.find()) {
            String matchString = matcher.group();
            matchString = matchString
                    .replace("[", "")
                    .replace("]", "");
            matchString = matchString.replaceAll("\"", "") + "字段类型错误";
            group += matchString;
        }
        return group;
    }
}
