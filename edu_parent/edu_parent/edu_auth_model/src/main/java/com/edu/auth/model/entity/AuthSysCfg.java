package com.edu.auth.model.entity;

import com.edu.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: edu_parent
 * @description: 接入系统配置表
 * @author: BaronLi
 * @create: 2019-06-21 09:35
 */
public class AuthSysCfg extends BaseEntity implements Serializable {
    private String asID;
    private String clientName;
    private String clientID;
    private String clientSecret;
    private String clientIP;
    private String clientKey;
    
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

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }
}
