package com.qf.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.entity.Email;
import com.qf.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * linzebin
 * 时间2019/6/29 12:00
 */
@Service
public class EmailServiceImpl implements IEmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @SuppressWarnings("unchecked")
    public String sendmail(Email email) throws Exception {
        Random random=new Random();
        int i = random.nextInt(9999);
        System.out.println("发送的随机数为"+i);
        //创建一封邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //创建一个邮件帮助对象
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(email.getSetFrom());
            messageHelper.addTo(email.getEmail(), "超级会员");
            messageHelper.setText(email.getText()+i,true);
            messageHelper.setSubject(email.getSubject());
            javaMailSender.send(mimeMessage);
                return i+"";
        }

    }



