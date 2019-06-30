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
    //去编辑  （编辑）
    @RequestMapping("/toupdate")
    public Object toupdate(){
        return "update";
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
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestParam String username) throws Exception {
        System.out.println(username);
        boolean b = userService.updateByUasername(username);
        return b;
    }

}
