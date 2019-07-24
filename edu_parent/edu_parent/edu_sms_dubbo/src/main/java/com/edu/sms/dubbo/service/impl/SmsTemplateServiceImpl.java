package com.edu.sms.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.edu.base.ResultVo;
import com.edu.sms.dubbo.service.SmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: edu_parent
 * @description:
 * @author: BaronLi
 * @create: 2019-07-23 16:30
 */
@Service
public class SmsTemplateServiceImpl implements SmsTemplateService {

    @Autowired
    private com.edu.sms.service.SmsTemplateService smsTemplateService;

    public ResultVo add(String code, String details, String remark) {
        return smsTemplateService.add(code, details, remark);
    }

    public ResultVo update(String stid, String code, String details, String remark) {
        return smsTemplateService.update(stid, code, details, remark);
    }

    public ResultVo delete(String stid) {
        return smsTemplateService.delete(stid);
    }

    public ResultVo selectBySTID(String stid) {
        return smsTemplateService.selectBySTID(stid);
    }

    public ResultVo findAll(String stid, String code, Integer pageNo, Integer pageRow) {
        return smsTemplateService.findAll(stid, code, pageNo, pageRow);
    }
}
