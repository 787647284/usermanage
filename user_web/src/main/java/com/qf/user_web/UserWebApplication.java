package com.qf.user_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.qf.controller")
public class UserWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserWebApplication.class, args);
    }

}
