package com.zb.misscmszb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.zb.misscmszb"})
@MapperScan(basePackages = {"com.zb.misscmszb.mapper"})
public class MisscmszbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MisscmszbApplication.class, args);
    }

}
