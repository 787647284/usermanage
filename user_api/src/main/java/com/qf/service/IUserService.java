package com.qf.service;

import com.qf.entity.User;

/**
 * linzebin
 * 时间2019/6/29 11:48
 */
public interface IUserService {
    //添加用户
    int addUser(User user,String email);
    //修改时查询
    boolean updateByUasername(String username) throws Exception;
    //根据id查询用户
    User getByid(int id);
    //修改
    int updateUser(User user);
    //登录验证
    boolean loginByUsername(String username);
    //登录
    boolean login(User user);
}
