package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.User;
import com.qf.service.IEmailService;
import com.qf.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * linzebin
 * 时间2019/7/1 0:40
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Reference
    IEmailService emailService;
    @Reference
    IUserService userService;
    //前往登录页面      （登录类）
    @RequestMapping("/tologon")

    public String tologon(){

        return "login";
    }
    //登录功能  （登录）
    @RequestMapping("/login")
    public Object login(User user, Model model){
        boolean login = userService.login(user);
        if(login){
            return "page";
        }else{
            model.addAttribute("msg","用户名密码错误");
            return "login";
        }
    }
    //用户是否存在
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestParam String username) throws Exception {
        System.out.println(username);
        boolean b = userService.updateByUasername(username);
            return b;
    }
    }
