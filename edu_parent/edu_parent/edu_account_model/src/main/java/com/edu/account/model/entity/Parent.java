package com.edu.account.model.entity;

import com.edu.base.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: edu_parent
 * @description: 家长表实体类
 * @author: BaronLi
 * @create: 2019-07-31 14:10
 */
@Data
public class Parent extends BaseEntity implements Serializable {
    private String aID;
    private String name;
    private String sex;
    private Date barfdate;
    private Integer age;

}


