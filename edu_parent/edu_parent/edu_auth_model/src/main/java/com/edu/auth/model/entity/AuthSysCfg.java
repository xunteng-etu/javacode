package com.edu.auth.model.entity;

import java.util.Date;

/**
 * @program: edu_parent
 * @description: 接入系统配置表
 * @author: BaronLi
 * @create: 2019-06-21 09:35
 */
public class AuthSysCfg {
    private String asID;
    private String clientName;
    private String clientID;
    private String clientSecret;
    private String clientIP;
    private int serial;
    private String remark;
    private String sysStatus;
    private Date createTime;
    private Date updateTime;
    private String clientKey;
    private String valueFlag;

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

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSysStatus() {
        return sysStatus;
    }

    public void setSysStatus(String sysStatus) {
        this.sysStatus = sysStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public String getValueFlag() {
        return valueFlag;
    }

    public void setValueFlag(String valueFlag) {
        this.valueFlag = valueFlag;
    }
}
