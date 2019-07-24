package com.edu.sms.dubbo.service;

import com.edu.base.ResultVo;

public interface SendSmsService {
    /**
     * 发送短信
     *
     * @param asid          接入系统标识
     * @param templatecode  短信模板ID
     * @param telephone     接收手机号
     * @param signName      短信签名名称
     * @param templateParam 短信模板变量对应的实际值，JSON格式
     * @param type          1:即时发送，3：延迟发送
     * @return
     */
    ResultVo send(String asid, String templatecode, String telephone, String signName, String templateParam, String type);

    /**
     * 短信即时发送接口
     *
     * @param asid          接入系统标识
     * @param templatecode  短信模板ID
     * @param telephone     接收手机号
     * @param signName      短信签名名称
     * @param templateParam 短信模板变量对应的实际值，JSON格式
     * @return
     */
    ResultVo sendSms(String asid, String templatecode, String telephone, String signName, String templateParam);

    /**
     * 添加到待发送表
     *
     * @param asid          接入系统标识
     * @param signName      短信签名名称
     * @param templatecode  短信模板ID
     * @param telephone     接收手机号
     * @param templateParam 短信模板变量对应的实际值，JSON格式
     * @return
     */
    ResultVo addSmsWait(String asid, String signName, String templatecode, String telephone, String templateParam);

}
