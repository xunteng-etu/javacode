package com.edu.account.model.entity;

import com.edu.base.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: edu_parent
 * @description: 账户表实体类
 * @author: BaronLi
 * @create: 2019-07-25 09:44
 */
@Data
public class Account extends BaseEntity implements Serializable {
    private String id;
    private String mobile;
    private String pw;
    private String wxOpenid;

}
