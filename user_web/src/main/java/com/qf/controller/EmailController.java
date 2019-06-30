package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.service.IEmailService;
import com.qf.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * linzebin
 * 时间2019/6/29 11:51
 */
@Controller
@RequestMapping("/email")
public class EmailController {
    @Reference
    IEmailService emailService;
    @Reference
    IUserService userService;
}
