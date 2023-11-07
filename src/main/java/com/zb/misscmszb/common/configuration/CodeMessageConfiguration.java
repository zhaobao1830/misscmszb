package com.zb.misscmszb.common.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
@ConfigurationProperties
@PropertySource(value = "classpath:config/exception-code.properties")
public class CodeMessageConfiguration {

    private static Map<Integer, String> codeMessage = new HashMap<>();

    public static String getMessage(Integer code) {
        return codeMessage.get(code);
    }

    public Map<Integer, String> getCodeMessage() {
        return codeMessage;
    }

    public void setCodeMessage(Map<Integer, String> codeMessage) {
        CodeMessageConfiguration.codeMessage = codeMessage;
    }
}
