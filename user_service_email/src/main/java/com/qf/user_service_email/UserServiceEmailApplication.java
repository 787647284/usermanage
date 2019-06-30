package com.qf.user_service_email;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.qf")
@DubboComponentScan("com.qf.serviceimpl")
public class UserServiceEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceEmailApplication.class, args);
    }

}
