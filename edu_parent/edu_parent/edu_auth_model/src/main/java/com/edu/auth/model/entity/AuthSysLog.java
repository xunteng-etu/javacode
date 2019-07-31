package com.edu.auth.model.entity;

import com.edu.base.entity.BaseEntity;
import lombok.Data;
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
@Data
public class AuthSysLog extends BaseEntity implements Serializable  {
    private String aslID;
    private String asID;
    private Date requestTime;
    private String mkToken;
    private String requestIP;
    private String isAuth;
    private String requestParm;

}
