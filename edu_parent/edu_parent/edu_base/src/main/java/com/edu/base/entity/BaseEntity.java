package com.edu.base.entity;

import lombok.Data;

import java.util.Date;

/**
 * @program: edu_parent
 * @description:
 * @author: BaronLi
 * @create: 2019-07-05 09:58
 */
@Data
public class BaseEntity {
    private Integer serial;
    private String remark;
    private String sysStatus;
    private Date createTime;
    private Date updateTime;
    private String valueFlag;
}
