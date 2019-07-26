package com.edu.account.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.account.dao.mapper.AccountMapper;
import com.edu.account.model.entity.Account;
import com.edu.base.Constant;
import com.edu.base.ResultVo;
import com.edu.sms.dubbo.service.SendSmsService;
import com.edu.utils.RandomUtils;
import com.edu.utils.RedisUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @program: edu_parent
 * @description: 账号业务逻辑层
 * @author: BaronLi
 * @create: 2019-07-25 14:02
 */
@Service
public class AccountService {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private AccountMapper accountMapper;
    @Reference
    private SendSmsService sendSmsService;

    /**
     * 查询手机号是否已注册
     *
     * @param mobile 手机号
     * @return
     */
    public ResultVo checkRegistered(String mobile) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(mobile)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        try {
            Account account = accountMapper.checkRegistered(mobile);
            if (account != null) {
                resultVo.setRt_code(Constant.RESULT_CODE_MOBILEISEXISTS);
                resultVo.setRt_msg(Constant.RESULT_MSG_MOBILEISEXISTS);
                resultVo.setRt_data(account);
            }
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVo;
    }

    /**
     * 创建密码服务
     *
     * @param id  账号ID
     * @param pwd 密码
     * @return
     */
    public ResultVo createPwd(String id, String pwd) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(id) || "".equals(pwd)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        String result = accountMapper.selectPwByID(id);
        if (result != null) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGSYSTEM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGSYSTEM);
            return resultVo;
        }
        try {
            //密码采用md5加密后截取前12位
            String password = DigestUtils.md5Hex(pwd).substring(0, 12);
            accountMapper.updatePwByID(password, id);
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVo;
    }


    /**
     * 手机注册验证码
     *
     * @param mobile       手机号码
     * @param asid         接入系统标识
     * @param templatecode 短信模板编号
     * @param signName     短信签名
     * @return
     */
    public ResultVo sendRegister(String mobile, String asid, String templatecode, String signName) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(mobile)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        String resule = checkRegistered(mobile).getRt_code();
        if (!"0".equals(resule)) {
            resultVo.setRt_code(Constant.RESULT_CODE_MOBILEISEXISTS);
            resultVo.setRt_msg(Constant.RESULT_MSG_MOBILEISEXISTS);
        }
        //生成随机6位数
        String code = RandomUtils.GET_RANDOMNUMBER(6);
        //短信模板
        String templateParam = "{\"code\":\"" + code + "\"}";
        //保存进redis,有效期为5分钟
        redisUtils.setObj(mobile + "_ResCode", code, 5, TimeUnit.MINUTES);
        try {
            resultVo = sendSmsService.sendSms(asid, templatecode, mobile, signName, templateParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVo;
    }
}
