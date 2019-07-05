package com.edu.sms.model.entity;

import com.edu.base.entity.BaseEntity;

/**
 * @program: edu_parent
 * @description: 发送短信记录表通用（成功，待发送）
 * @author: BaronLi
 * @create: 2019-07-05 09:47
 */
public class SmsBase extends BaseEntity {

    private String sid;
    private String asid;
    private String stid;
    private String telephone;
    private String templateparam;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getAsid() {
        return asid;
    }

    public void setAsid(String asid) {
        this.asid = asid;
    }

    public String getStid() {
        return stid;
    }

    public void setStid(String stid) {
        this.stid = stid;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTemplateparam() {
        return templateparam;
    }

    public void setTemplateparam(String templateparam) {
        this.templateparam = templateparam;
    }

}
