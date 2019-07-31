package com.edu.sms.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: edu_parent
 * @description: 发送短信失败记录表
 * @author: BaronLi
 * @create: 2019-07-05 11:19
 */
@Data
public class SmsError extends SmsBase implements Serializable {
    private Integer countFail;
    private String code;
    private String message;

}
