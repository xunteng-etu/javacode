package com.edu.sms.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.base.ResultVo;
import com.edu.sms.dubbo.service.SmsTemplateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: edu_parent
 * @description: 发送短信模板控制器
 * @author: BaronLi
 * @create: 2019-07-08 17:01
 */
@RestController
@RequestMapping("/smsTemplate")
public class SmsTemplateController {
    @Reference
    private SmsTemplateService smsTemplateService;

    /**
     * 删除（逻辑删除）
     *
     * @param stid 短信模板标识
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResultVo delete(@RequestParam(value = "stid", defaultValue = "") String stid) {
        return smsTemplateService.delete(stid);
    }

    /**
     * 获取单条数据
     *
     * @param stid 短信模板标识
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultVo get(@RequestParam(value = "stid", defaultValue = "") String stid) {
        return smsTemplateService.selectBySTID(stid);
    }

    /**
     * 修改
     *
     * @param code    短信平台模板编号
     * @param details 短信模板内容
     * @param remark  备注
     * @param stid    短信模板标识
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResultVo update(@RequestParam(value = "code", defaultValue = "") String code,
                           @RequestParam(value = "details", defaultValue = "") String details,
                           @RequestParam(value = "remark", defaultValue = "") String remark,
                           @RequestParam(value = "stid", defaultValue = "") String stid) {
        if(!"".equals(stid)){
            return smsTemplateService.update(stid, code, details, remark);
        }else {
            return smsTemplateService.add(code, details, remark);
        }

    }

    /**
     * 分页查询短信模板
     *
     * @param stid    短信模板标识
     * @param code    短信平台模板编号
     * @param pageNo  页码，为空时默认第一页
     * @param pageRow 一页行数，为空时默认30行
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultVo findAll(@RequestParam(value = "stid", defaultValue = "") String stid,
                            @RequestParam(value = "code", defaultValue = "") String code,
                            @RequestParam(value = "pageNo", defaultValue = "") Integer pageNo,
                            @RequestParam(value = "pageRow", defaultValue = "") Integer pageRow) {
        return smsTemplateService.findAll(stid, code, pageNo, pageRow);
    }

}
