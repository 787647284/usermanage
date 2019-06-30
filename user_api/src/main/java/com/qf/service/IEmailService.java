package com.qf.service;


import com.qf.entity.Email;

/**
 * linzebin
 * 时间2019/6/29 11:49
 */
public interface IEmailService {
        String sendmail(Email email) throws Exception;
}
