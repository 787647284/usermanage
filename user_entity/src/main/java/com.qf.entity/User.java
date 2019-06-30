package com.qf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * linzebin
 * 时间2019/6/29 11:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user")
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private int id;
    private String username;
    private String password;
    private String email;
    private String nickname;
    @TableField(exist = false)
    private String coco;
}
