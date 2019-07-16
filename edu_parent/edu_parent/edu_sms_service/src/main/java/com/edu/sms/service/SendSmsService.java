package com.edu.sms.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.edu.base.Constant;
import com.edu.base.ResultVo;
import com.edu.sms.model.entity.SmsBase;
import com.edu.sms.model.entity.SmsError;
import com.edu.sms.model.mapper.SmsErrorMapper;
import com.edu.sms.model.mapper.SmsSuccessMapper;
import com.edu.sms.model.mapper.SmsWaitMapper;
import com.edu.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    private static final String regionId = "";
    private static final String accessKeyId = "";
    private static final String accessSecret = "";
    private static final String sendSmsAddress = "dysmsapi.aliyuncs.com";
    private static final String sendSmsVersion = "2017-05-25";
    private static final String sendSmsAction = "SendSms";

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

        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(sendSmsAddress);
        request.setVersion(sendSmsVersion);
        request.setAction(sendSmsAction);
        request.putQueryParameter("PhoneNumbers", telephone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templatecode);
        request.putQueryParameter("TemplateParam", templateParam);
        String sid = RandomUtils.GET_RANDOMSTRING(12);
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            SmsBase smsBase = new SmsBase();
            SmsBase checkSid = smsSuccessMapper.selectBySid(sid);
            if (checkSid != null) {
                sid = RandomUtils.GET_RANDOMSTRING(12);
            }
            smsBase.setSid(sid);
            smsBase.setAsid(asid);
            smsBase.setTemplatecode(templatecode);
            smsBase.setSignname(signName);
            smsBase.setTelephone(telephone);
            smsBase.setTemplateparam(templateParam);
            smsBase.setRemark("发送成功");
            smsBase.setSysStatus("3");
            smsBase.setCreateTime(new Date());
            smsBase.setValueFlag("1");
            smsSuccessMapper.insert(smsBase);
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
        } catch (Exception e) {
            SmsError smsError = new SmsError();
            SmsError checkSid = smsErrorMapper.selectBySid(sid);
            if (checkSid != null) {
                sid = RandomUtils.GET_RANDOMSTRING(12);
            }
            smsError.setSid(sid);
            smsError.setAsid(asid);
            smsError.setTemplatecode(templatecode);
            smsError.setTelephone(telephone);
            smsError.setCode("错误码");
            smsError.setMessage("错误信息");
            smsError.setTemplateparam(templateParam);
            //smsError.setRemark("发送失败");
            smsError.setCountFail(1);
            smsError.setSysStatus("5");
            smsError.setCreateTime(new Date());
            smsError.setValueFlag("1");
            smsErrorMapper.insert(smsError);
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
        SmsBase checkSid = smsWaitMapper.selectBySid(sid);
        if (checkSid != null) {
            sid = RandomUtils.GET_RANDOMSTRING(12);
        }
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
}
