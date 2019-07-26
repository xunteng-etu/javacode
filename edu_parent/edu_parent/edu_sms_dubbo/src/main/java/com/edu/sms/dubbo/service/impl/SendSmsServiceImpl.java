package com.edu.sms.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.edu.base.ResultVo;
import com.edu.sms.dubbo.service.SendSmsService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: edu_parent
 * @description:
 * @author: BaronLi
 * @create: 2019-07-23 16:26
 */
@Service(timeout = 5000,retries = -1)
public class SendSmsServiceImpl implements SendSmsService {

    @Autowired
    private com.edu.sms.service.SendSmsService sendSmsService;

    public ResultVo send(String asid, String templatecode, String telephone, String signName, String templateParam, String type) {
        return sendSmsService.send(asid, templatecode, telephone, signName, templateParam, type);
    }

    public ResultVo sendSms(String asid, String templatecode, String telephone, String signName, String templateParam) {
        return sendSmsService.sendSms(asid, templatecode, telephone, signName, templateParam);
    }

    public ResultVo addSmsWait(String asid, String signName, String templatecode, String telephone, String templateParam) {
        return sendSmsService.addSmsWait(asid, signName, templatecode, telephone, templateParam);
    }
}
