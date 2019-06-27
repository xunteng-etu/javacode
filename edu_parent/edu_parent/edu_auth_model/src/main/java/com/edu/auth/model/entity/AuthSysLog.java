package com.edu.auth.model.entity;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @program: edu_parent
 * @description: 认证记录表
 * @author: BaronLi
 * @create: 2019-06-21 09:36
 */
public class AuthSysLog {
    private String aslID;
    private String asID;
    private Date requestTime;
    private String mkToken;
    private String requestIP;
    private String isAuth;
    private String requestParm;
    private int serial;
    private String remark;
    private String sysStatus;
    private Date createTime;
    private Date updateTime;
    private String valueFlag;

    public String getAslID() {
        return aslID;
    }

    public void setAslID(String aslID) {
        this.aslID = aslID;
    }

    public String getAsID() {
        return asID;
    }

    public void setAsID(String asID) {
        this.asID = asID;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getMkToken() {
        return mkToken;
    }

    public void setMkToken(String mkToken) {
        this.mkToken = mkToken;
    }

    public String getRequestIP() {
        return requestIP;
    }

    public void setRequestIP(String requestIP) {
        this.requestIP = requestIP;
    }

    public String getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(String isAuth) {
        this.isAuth = isAuth;
    }

    public String getRequestParm() {
        return requestParm;
    }

    public void setRequestParm(String requestParm) {
        this.requestParm = requestParm;
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

    public String getValueFlag() {
        return valueFlag;
    }

    public void setValueFlag(String valueFlag) {
        this.valueFlag = valueFlag;
    }

    @Override
    public String toString() {
        return "AuthSysLog{" +
                "aslID='" + aslID + '\'' +
                ", asID='" + asID + '\'' +
                ", requestTime=" + requestTime +
                ", mkToken='" + mkToken + '\'' +
                ", requestIP='" + requestIP + '\'' +
                ", isAuth='" + isAuth + '\'' +
                ", requestParm='" + requestParm + '\'' +
                ", serial=" + serial +
                ", remark='" + remark + '\'' +
                ", sysStatus='" + sysStatus + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", valueFlag='" + valueFlag + '\'' +
                '}';
    }

    public static void main(String[] args){
        System.out.println(RandomStringUtils.randomAlphanumeric(16));
        System.out.println(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
    }
}
