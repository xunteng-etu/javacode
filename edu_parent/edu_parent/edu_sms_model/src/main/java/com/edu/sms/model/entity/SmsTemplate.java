package com.edu.sms.model.entity;

import com.edu.base.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: edu_parent
 * @description: 短信模板实体类
 * @author: BaronLi
 * @create: 2019-07-05 09:54
 */
@Data
public class SmsTemplate extends BaseEntity implements Serializable {
    private String stid;
    private String code;
    private String details;

}
