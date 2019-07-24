package com.edu.sms.model.entity;

import com.edu.base.entity.BaseEntity;

import java.io.Serializable;

/**
 * @program: edu_parent
 * @description: 发送短信记录表通用（成功，待发送）
 * @author: BaronLi
 * @create: 2019-07-05 09:47
 */
public class SmsBase extends BaseEntity implements Serializable {

    private String sid;
    private String asid;
    private String telephone;
    private String templateparam;
    private String signname;
    private String templatecode;

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

    public String getSignname() {
        return signname;
    }

    public void setSignname(String signname) {
        this.signname = signname;
    }

    public String getTemplatecode() {
        return templatecode;
    }

    public void setTemplatecode(String templatecode) {
        this.templatecode = templatecode;
    }
}
