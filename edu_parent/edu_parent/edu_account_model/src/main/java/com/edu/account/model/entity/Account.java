package com.edu.account.model.entity;

import com.edu.base.entity.BaseEntity;

import java.io.Serializable;

/**
 * @program: edu_parent
 * @description: 账户表实体类
 * @author: BaronLi
 * @create: 2019-07-25 09:44
 */
public class Account extends BaseEntity implements Serializable {
    private String id;
    private String mobile;
    private String pw;
    private String wxOpenid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }
}
