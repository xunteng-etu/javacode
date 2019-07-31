package com.edu.auth.model.entity;

import com.edu.base.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: edu_parent
 * @description: 接入系统配置表
 * @author: BaronLi
 * @create: 2019-06-21 09:35
 */
@Data
public class AuthSysCfg extends BaseEntity implements Serializable {
    private String asID;
    private String clientName;
    private String clientID;
    private String clientSecret;
    private String clientIP;
    private String clientKey;

}
