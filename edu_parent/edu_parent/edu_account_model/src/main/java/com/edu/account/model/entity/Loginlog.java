package com.edu.account.model.entity;

import com.edu.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: edu_parent
 * @description: 登录记录表实体类
 * @author: BaronLi
 * @create: 2019-07-29 15:56
 */
public class Loginlog extends BaseEntity implements Serializable {
    private String loginlogID;
    private String asID;
    private String clientName;
    private String aID;
    private String loginType;
    private Date loginTime;

    public String getLoginlogID() {
        return loginlogID;
    }

    public void setLoginlogID(String loginlogID) {
        this.loginlogID = loginlogID;
    }

    public String getAsID() {
        return asID;
    }

    public void setAsID(String asID) {
        this.asID = asID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getaID() {
        return aID;
    }

    public void setaID(String aID) {
        this.aID = aID;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
