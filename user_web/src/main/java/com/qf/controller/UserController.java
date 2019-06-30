package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Email;
import com.qf.entity.User;
import com.qf.service.IEmailService;
import com.qf.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

//注册   登录  邮箱
/**
 * linzebin
 * 时间2019/6/29 11:51
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Reference
    IEmailService emailService;
    @Reference
    IUserService userService;
    //前往注册页面   （注册类）
    @RequestMapping("/toregister")
    public String toregister(){

        return "register";
    }
    //前往登录页面      （登录类）
    @RequestMapping("/tologon")

    public String tologon(){

        return "login";
    }
    //发送验证码功能   （邮箱类）
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
    //去编辑  （登录）
    @RequestMapping("/toupdate")
    public Object toupdate(){

        return "update";
    }
    //查看该用户是否存在 （编辑）
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestParam  String username) throws Exception {
        System.out.println(username);
        boolean b = userService.updateByUasername(username);
        return b;
    }
    //去修改用户 （编辑）
    @RequestMapping("/toUpdateUser")
    public Object toUpdateUser(@RequestParam int id,Model model){
        System.out.println("执行了找回！！！！！！！！！！！！！！！！");
        model.addAttribute("id",id);
        return "updateUser";
    }
    //修改用户（编辑）
    @RequestMapping("/updateUser")
    public Object updateUserByid(User user){
        System.out.println("进入修改!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        User user1 = userService.getByid(user.getId());
        user1.setPassword(user.getPassword());
        int i = userService.updateUser(user1);
        System.out.println("返回值"+i);
        if(i>0){
            return "login";
        }
        return null;
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
    //登录功能  （登录）
    @RequestMapping("/login")
    public Object login(User user,Model model){
        boolean login = userService.login(user);
        if(login){
            return "page";
        }else{
            model.addAttribute("msg","用户名密码错误");
            return "login";
        }
    }
}
