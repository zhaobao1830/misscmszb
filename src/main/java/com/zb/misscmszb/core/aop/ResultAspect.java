package com.zb.misscmszb.core.aop;

import com.zb.misscmszb.core.configuration.CodeMessageConfiguration;
import com.zb.misscmszb.vo.UnifyResponseVO;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 对返回结果为UnifyResponseVO的控制器层方法进行处理
 * 如果message为null，会通过code获取对应信息
 *
 */
@Aspect
@Component
public class ResultAspect {
    @AfterReturning(returning = "result", pointcut = "execution(public * com.zb.misscmszb.controller..*.*(..))")
    public void doAfterReturning(UnifyResponseVO<String> result) {
        int code = result.getCode();
        String messageOfVO = result.getMessage();
        // code-message.properties 中配置的 message
        String messageOfConfiguration = CodeMessageConfiguration.getMessage(code);

        // 如果 code-message.properties 中指定了相应的 message 并且 UnifyResponseVO 的 message 为null
        // 则使用 messageOfConfiguration 替换 messageOfVO
        if (StringUtils.hasText(messageOfConfiguration) && !StringUtils.hasText(messageOfVO)) {
            result.setMessage(messageOfConfiguration);
        }
    }
}
