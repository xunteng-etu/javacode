package com.edu.auth.model.entity;

import com.edu.base.entity.BaseEntity;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @program: edu_parent
 * @description: 认证记录表
 * @author: BaronLi
 * @create: 2019-06-21 09:36
 */
public class AuthSysLog extends BaseEntity implements Serializable  {
    private String aslID;
    private String asID;
    private Date requestTime;
    private String mkToken;
    private String requestIP;
    private String isAuth;
    private String requestParm;

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

    public static void main(String[] args){
        System.out.println(RandomStringUtils.randomAlphanumeric(16));
        System.out.println(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
    }
}
