package com.edu.sms.service;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.edu.base.Constant;
import com.edu.base.ResultVo;
import com.edu.sms.dao.mapper.SmsSuccessMapper;
import com.edu.sms.dao.mapper.SmsWaitMapper;
import com.edu.sms.model.entity.SmsBase;
import com.edu.sms.model.entity.SmsError;
import com.edu.sms.dao.mapper.SmsErrorMapper;
import com.edu.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @program: edu_parent
 * @description: 发送短信业务逻辑层
 * @author: BaronLi
 * @create: 2019-07-10 14:07
 */
@Service
public class SendSmsService {
    @Autowired
    private SmsSuccessMapper smsSuccessMapper;
    @Autowired
    private SmsErrorMapper smsErrorMapper;
    @Autowired
    private SmsWaitMapper smsWaitMapper;



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
    public ResultVo send(String asid, String templatecode, String telephone, String signName, String templateParam, String type) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(asid) || "".equals(templatecode) || "".equals(telephone)
                || "".equals(type) || "".equals(signName) || "".equals(templateParam)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        if ("1".equals(type)) {
            return sendSms(asid, templatecode, telephone, signName, templateParam);
        } else if ("3".equals(type)) {
            return addSmsWait(asid, signName, templatecode, telephone, templateParam);
        } else {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
    }

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
    public ResultVo sendSms(String asid, String templatecode, String telephone, String signName, String templateParam) {
        ResultVo resultVo = new ResultVo();
        DefaultProfile profile = DefaultProfile.getProfile(Constant.regionId, Constant.accessKeyId, Constant.accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(Constant.sendSmsAddress);
        request.setVersion(Constant.sendSmsVersion);
        request.setAction(Constant.sendSmsAction);
        request.putQueryParameter("PhoneNumbers", telephone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templatecode);
        request.putQueryParameter("TemplateParam", templateParam);
        String sid = RandomUtils.GET_RANDOMSTRING(12);
        try {
            CommonResponse response = client.getCommonResponse(request);
            Map map = (Map)JSON.parse(response.getData());
            //请求成功
            if("OK".equals(map.get("Code"))){
                SmsBase smsBase = new SmsBase();
                smsBase.setSid(sid);
                smsBase.setAsid(asid);
                smsBase.setTemplatecode(templatecode);
                smsBase.setSignname(signName);
                smsBase.setTelephone(telephone);
                smsBase.setTemplateparam(templateParam);
                smsBase.setRemark(map.get("Message").toString());
                smsBase.setSysStatus("3");
                smsBase.setCreateTime(new Date());
                smsBase.setValueFlag("1");
                smsSuccessMapper.insert(smsBase);
            }else{
                SmsError smsError = new SmsError();
                smsError.setSid(sid);
                smsError.setAsid(asid);
                smsError.setTemplatecode(templatecode);
                smsError.setTelephone(telephone);
                smsError.setCode(map.get("Code").toString());
                smsError.setMessage(map.get("Message").toString());
                smsError.setTemplateparam(templateParam);
                smsError.setRemark("请求失败");
                smsError.setCountFail(1);
                smsError.setSysStatus("5");
                smsError.setCreateTime(new Date());
                smsError.setValueFlag("1");
                smsErrorMapper.insert(smsError);
            }
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
            resultVo.setRt_data(map);
        } catch (Exception e) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGSYSTEM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGSYSTEM);
            e.printStackTrace();
        }
        return resultVo;
    }

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
    public ResultVo addSmsWait(String asid, String signName, String templatecode, String telephone, String templateParam) {
        ResultVo resultVo = new ResultVo();
        String sid = RandomUtils.GET_RANDOMSTRING(12);
        try {
            SmsBase smsBase = new SmsBase();
            smsBase.setAsid(asid);
            smsBase.setSid(sid);
            smsBase.setSignname(signName);
            smsBase.setTemplatecode(templatecode);
            smsBase.setTelephone(telephone);
            smsBase.setTemplateparam(templateParam);
            smsBase.setSysStatus("0");
            smsBase.setCreateTime(new Date());
            smsBase.setValueFlag("1");
            smsWaitMapper.insert(smsBase);
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
        } catch (Exception e) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGSYSTEM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGSYSTEM);
            e.printStackTrace();
        }
        return resultVo;
    }

    public static void main(String[] args) {
        String result = "{\"Message\":\"OK\",\"RequestId\":\"598D34B5-7AF1-4F42-ABFF-84F82B98CADA\",\"BizId\":\"392700763851915113^0\",\"Code\":\"OK\"}";
        Map map = (Map) JSON.parse(result);
        System.out.println(map.get("Message").toString());

    }
}
