package com.edu.sms.model.entity;

import com.edu.base.BaseEntity;

import java.util.Date;

/**
 * @program: edu_parent
 * @description: 短信模板实体类
 * @author: BaronLi
 * @create: 2019-07-05 09:54
 */
public class SmsTemplate extends BaseEntity {
    private String stid;
    private String code;
    private String details;

    public String getStid() {
        return stid;
    }

    public void setStid(String stid) {
        this.stid = stid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
