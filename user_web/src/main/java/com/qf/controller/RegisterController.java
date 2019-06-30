package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Email;
import com.qf.entity.User;
import com.qf.service.IEmailService;
import com.qf.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * linzebin
 * 时间2019/7/1 0:40
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
    @Reference
    IEmailService emailService;
    @Reference
    IUserService userService;
    //前往注册页面   （注册类）
    @RequestMapping("/toregister")
    public String toregister(){

        return "register";
    }
    //注册发送邮件
    @ResponseBody
    @RequestMapping("/register")
    public Object register(@RequestParam String email, HttpSession session) throws Exception {
        Email email1=new Email();
        email1.setEmail(email);
        email1.setText("验证码为");
        email1.setSubject("注册验证！");
        String sendmail = emailService.sendmail(email1);
        session.setAttribute("sendmail",sendmail);
        System.out.println("存入session的验证码为"+sendmail);
        return 1;
    }
    //注册功能   （注册类）
    @ResponseBody
    @RequestMapping("/adduser")
    public Object adduser(@RequestBody User user, HttpSession session){
        String  sendmail = (String) session.getAttribute("sendmail");
        int i = userService.addUser(user, sendmail);
        return i;
    }
    //用户名重复校验   （注册）
    @ResponseBody
    @RequestMapping("/loginverify")
    public Object loginverify(@RequestParam  String username){
        System.out.println("用户名为"+username);
        boolean b = userService.loginByUsername(username);
        System.out.println(b);
        return b;
    }
    //前往登录页面      （登录类）
    @RequestMapping("/tologon")

    public String tologon(){

        return "login";
    }
}
