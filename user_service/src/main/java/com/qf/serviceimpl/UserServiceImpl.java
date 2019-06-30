package com.qf.serviceimpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.dao.UserMapper;
import com.qf.entity.Email;
import com.qf.entity.User;
import com.qf.service.IEmailService;
import com.qf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * linzebin
 * 时间2019/6/29 11:56
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Reference
    IEmailService emailService;
    @Override
    public int addUser(User user, String sendmail) {
        System.out.println("coco"+user.getCoco()+"后台"+sendmail);
        String coco = user.getCoco();
        if(sendmail.equals(coco)){
            userMapper.insert(user);
            return 1;
        }
        return 0;
    }
    @Override
    public boolean updateByUasername(String username) throws Exception {
        //根据用户名查询该用户是否存在
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println("找回的用户为"+user);
        if (user!=null){
            Email email1=new Email();
            email1.setEmail("z3316504@163.com");
            email1.setText("请点击<a href='http://localhost:8888/user/toUpdateUser?id="+user.getId()+"'>这里</a>找回密码~");
            //?id="+user.getId()+
            email1.setSubject("找回密码通知");
            emailService.sendmail(email1);
            System.out.println("发送找回密码邮件");
            return true;
        }
        return false;
    }

    @Override
    public User getByid(int id) {

        return  userMapper.selectById(id);
    }

    @Override
    public int updateUser(User user) {
    userMapper.updateById(user);
            return 1;
    }

    @Override
    public boolean loginByUsername(String username) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println("验证的用户名"+user);
        if(user!=null){
                return false;
        }
        return true;
    }

    @Override
    public boolean login(User user) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("username",user.getUsername());
        queryWrapper.eq("password",user.getPassword());
        User user1 = userMapper.selectOne(queryWrapper);
        if(user1!=null){
            return true;
        }
        return false;
    }
}
