package com.edu.sms.service;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.edu.base.Constant;
import com.edu.sms.dao.mapper.SmsSuccessMapper;
import com.edu.sms.dao.mapper.SmsWaitMapper;
import com.edu.sms.model.entity.SmsBase;
import com.edu.sms.model.entity.SmsError;
import com.edu.sms.dao.mapper.SmsErrorMapper;
import com.edu.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: edu_parent
 * @description: 任务调度
 * @author: BaronLi
 * @create: 2019-07-11 16:18
 */
@Service
public class TimeQuartz {

    @Autowired
    private SmsWaitMapper smsWaitMapper;
    @Autowired
    private SmsSuccessMapper smsSuccessMapper;
    @Autowired
    private SmsErrorMapper smsErrorMapper;

    @Scheduled(cron = "0 0/1 9-21 * * ?")//早上九点到晚上九点，每一分钟执行一次
    public void sendBatchSms() {
        //每次查询500条数据
        List<SmsBase> smsBases = smsWaitMapper.selectBySysStatus("0", 500);
        if (smsBases != null && smsBases.size() != 0) {
            for (int i = 0; i < smsBases.size(); i++) {
                //修改短信状态
                smsWaitMapper.updateSysStatus(smsBases.get(i).getSid(), "1");
                //发送...
                DefaultProfile profile = DefaultProfile.getProfile(Constant.regionId, Constant.accessKeyId, Constant.accessSecret);
                IAcsClient client = new DefaultAcsClient(profile);

                CommonRequest request = new CommonRequest();
                request.setMethod(MethodType.POST);
                request.setDomain(Constant.sendSmsAddress);
                request.setVersion(Constant.sendSmsVersion);
                request.setAction(Constant.sendSmsAction);
                request.putQueryParameter("PhoneNumbers", smsBases.get(i).getTelephone());
                request.putQueryParameter("SignName", smsBases.get(i).getSignname());
                request.putQueryParameter("TemplateCode", smsBases.get(i).getTemplatecode());
                request.putQueryParameter("TemplateParam", smsBases.get(i).getTemplateparam());
                String sid = RandomUtils.GET_RANDOMSTRING(12);
                try {
                    CommonResponse response = client.getCommonResponse(request);
                    Map map = (Map) JSON.parse(response.getData());
                    if ("OK".equals(map.get("Code"))) {
                        //成功...
                        SmsBase smsBase = new SmsBase();
                        smsBase.setSid(sid);
                        smsBase.setAsid(smsBases.get(i).getAsid());
                        smsBase.setTemplatecode(smsBases.get(i).getTemplatecode());
                        smsBase.setSignname(smsBases.get(i).getSignname());
                        smsBase.setTelephone(smsBases.get(i).getTelephone());
                        smsBase.setTemplateparam(smsBases.get(i).getTemplateparam());
                        smsBase.setRemark(map.get("Message").toString());
                        smsBase.setSysStatus("3");
                        smsBase.setCreateTime(new Date());
                        smsBase.setValueFlag("1");
                        //把数据移到成功表
                        smsSuccessMapper.insert(smsBase);
                    } else {
                        //失败...
                        SmsError smsError = new SmsError();
                        smsError.setSid(sid);
                        smsError.setAsid(smsBases.get(i).getAsid());
                        smsError.setTemplatecode(smsBases.get(i).getTemplatecode());
                        smsError.setTelephone(smsBases.get(i).getTelephone());
                        smsError.setCode(map.get("Code").toString());
                        smsError.setMessage(map.get("Message").toString());
                        smsError.setTemplateparam(smsBases.get(i).getTemplateparam());
                        smsError.setSignname(smsBases.get(i).getSignname());
                        smsError.setRemark("请求失败");
                        smsError.setCountFail(1);
                        smsError.setSysStatus("5");
                        smsError.setCreateTime(new Date());
                        smsError.setValueFlag("1");
                        //把数据移到失败表
                        smsErrorMapper.insert(smsError);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                smsWaitMapper.delete(smsBases.get(i).getSid());
            }
        }
    }

    @Scheduled(cron = "0 0/5 9-21 * * ?")//早上九点到晚上九点，每五分钟执行一次
    public void sendBatchSmsByError() {
        //每次查询1000条数据
        List<SmsError> smsErrors = smsErrorMapper.selectBySysStatus("5", 1000);
        if (smsErrors != null && smsErrors.size() != 0) {
            for (int i = 0; i < smsErrors.size(); i++) {
                //修改发送状态
                smsErrorMapper.updateSysStatus(smsErrors.get(i).getSid(), "1");
                //发送...
                DefaultProfile profile = DefaultProfile.getProfile(Constant.regionId, Constant.accessKeyId, Constant.accessSecret);
                IAcsClient client = new DefaultAcsClient(profile);

                CommonRequest request = new CommonRequest();
                request.setMethod(MethodType.POST);
                request.setDomain(Constant.sendSmsAddress);
                request.setVersion(Constant.sendSmsVersion);
                request.setAction(Constant.sendSmsAction);
                request.putQueryParameter("PhoneNumbers", smsErrors.get(i).getTelephone());
                request.putQueryParameter("SignName", smsErrors.get(i).getSignname());
                request.putQueryParameter("TemplateCode", smsErrors.get(i).getTemplatecode());
                request.putQueryParameter("TemplateParam", smsErrors.get(i).getTemplateparam());
                String sid = RandomUtils.GET_RANDOMSTRING(12);
                try {
                    CommonResponse response = client.getCommonResponse(request);
                    Map map = (Map) JSON.parse(response.getData());
                    if ("OK".equals(map.get("Code"))) {
                        //成功...
                        SmsBase smsBase = new SmsBase();
                        smsBase.setSid(sid);
                        smsBase.setAsid(smsErrors.get(i).getAsid());
                        smsBase.setTemplatecode(smsErrors.get(i).getTemplatecode());
                        smsBase.setSignname(smsErrors.get(i).getSignname());
                        smsBase.setTelephone(smsErrors.get(i).getTelephone());
                        smsBase.setTemplateparam(smsErrors.get(i).getTemplateparam());
                        smsBase.setRemark("发送成功");
                        smsBase.setSysStatus("3");
                        smsBase.setCreateTime(new Date());
                        smsBase.setValueFlag("1");
                        //把数据移到成功表
                        smsSuccessMapper.insert(smsBase);
                        smsErrorMapper.delete(smsErrors.get(i).getSid());
                    } else {
                        //失败,次数累计
                        smsErrorMapper.updateCountFail(smsErrors.get(i).getCountFail() + 1, smsErrors.get(i).getSid());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
