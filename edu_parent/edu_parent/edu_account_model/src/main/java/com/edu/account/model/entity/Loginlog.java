package com.edu.account.model.entity;

import com.edu.base.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: edu_parent
 * @description: 登录记录表实体类
 * @author: BaronLi
 * @create: 2019-07-29 15:56
 */
@Data
public class Loginlog extends BaseEntity implements Serializable {
    private String loginlogID;
    private String asID;
    private String clientName;
    private String aID;
    private String loginType;
    private Date loginTime;

}
