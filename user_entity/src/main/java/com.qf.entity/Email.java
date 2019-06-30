package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * linzebin
 * 时间2019/6/29 19:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email implements Serializable {
    //发件人
    String setFrom="verygoodwlk@sina.cn";
    //收件人
    String email;
    //发送内容
    String Text;
    //主题
    String Subject;

}
