package com.edu.sms.model.entity;

import com.edu.base.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: edu_parent
 * @description: 发送短信记录表通用（成功，待发送）
 * @author: BaronLi
 * @create: 2019-07-05 09:47
 */
@Data
public class SmsBase extends BaseEntity implements Serializable {
    private String sid;
    private String asid;
    private String telephone;
    private String templateparam;
    private String signname;
    private String templatecode;

}
