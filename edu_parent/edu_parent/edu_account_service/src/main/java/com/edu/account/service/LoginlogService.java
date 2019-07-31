package com.edu.account.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.account.dao.mapper.AccountMapper;
import com.edu.account.dao.mapper.LoginlogMapper;
import com.edu.account.model.entity.Account;
import com.edu.account.model.entity.Loginlog;
import com.edu.base.Constant;
import com.edu.base.ResultVo;
import com.edu.sms.dubbo.service.SendSmsService;
import com.edu.utils.RandomUtils;
import com.edu.utils.RedisUtils;
import com.edu.utils.TokenUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: edu_parent
 * @description: 账号登录逻辑层
 * @author: BaronLi
 * @create: 2019-07-29 16:30
 */
@Service
public class LoginlogService {

    @Autowired
    private AccountService accountService;
    @Autowired
    private RedisUtils redisUtils;
    @Reference
    private SendSmsService sendSmsService;
    @Autowired
    private LoginlogMapper loginlogMapper;
    @Autowired
    private AccountMapper accountMapper;


    /**
     * 用户手机登录验证码发送功能
     *
     * @param mobile 手机号码
     * @param asid   接入系统标识
     * @return
     */
    public ResultVo sendLoginCode(String mobile, String asid) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(mobile) || "".equals(asid)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        ResultVo result = accountService.checkRegistered(mobile);
        if (result.getRt_data() == null) {
            resultVo.setRt_code(Constant.RESULT_CODE_MOBILEISNOEXISTS);
            resultVo.setRt_msg(Constant.RESULT_MSG_MOBILEISNOEXISTS);
            return resultVo;
        }
        //生成随机6位数
        String code = RandomUtils.GET_RANDOMNUMBER(6);
        //短信模板
        String templateParam = "{\"code\":\"" + code + "\"}";
        //保存进redis,有效期为5分钟
        redisUtils.setStr(mobile + Constant.REDIS_KEY_LOGIN_CODE, code, 5, TimeUnit.MINUTES);
        try {
            resultVo = sendSmsService.sendSms(asid, Constant.login_code, mobile, Constant.signName, templateParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVo;
    }

    /**
     * 短信验证码登录
     *
     * @param mobile     手机号码
     * @param code       验证码
     * @param asid       接入系统标识
     * @param clientName 接入系统名称
     * @return
     */
    public ResultVo codeLogin(String mobile, String code, String asid, String clientName) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(mobile) || "".equals(code) || "".equals(asid) || "".equals(clientName)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        ResultVo result = accountService.checkRegistered(mobile);
        if (result.getRt_data() == null) {
            resultVo.setRt_code(Constant.RESULT_CODE_MOBILEISNOEXISTS);
            resultVo.setRt_msg(Constant.RESULT_MSG_MOBILEISNOEXISTS);
            return resultVo;
        }
        try {
            Account account = (Account) result.getRt_data();

            //获取redis里的验证码
            String confirmCode = redisUtils.getStr(mobile + Constant.REDIS_KEY_LOGIN_CODE);
            if (confirmCode == null || !code.equals(confirmCode)) {
                resultVo.setRt_code(Constant.RESULT_CODE_WONGCODE);
                resultVo.setRt_msg(Constant.RESULT_MSG_WONGCODE);
                return resultVo;
            }
            Map map = new HashMap();
            map.put("mobile", mobile);
            map.put("code", confirmCode);
            map.put("sta", new Date().getTime());//时间戳，保证每次生成的token都不一样
            String token = TokenUtils.creatToken(map, RandomUtils.GET_RANDOMSTRING(32).getBytes());

            //把token存放进redis,有效期：7天
            redisUtils.setObj(token + Constant.REDIS_KEY_LOGIN, account, 7, TimeUnit.DAYS);
            //添加到登录信息表
            String id = RandomUtils.GET_RANDOMNUMBER(12);
            Loginlog check = loginlogMapper.getByID(id);
            if (check != null) {
                id = RandomUtils.GET_RANDOMNUMBER(12);
            }
            Loginlog loginlog = new Loginlog();
            loginlog.setLoginlogID(id);
            loginlog.setAsID(asid);
            loginlog.setClientName(clientName);

            //账户标识
            loginlog.setaID(account.getId());
            loginlog.setLoginType("1");//1.验证码 3.密码
            loginlog.setLoginTime(new Date());
            loginlog.setSerial(0);
            loginlog.setSysStatus("1");
            loginlog.setCreateTime(new Date());
            loginlog.setValueFlag("1");
            loginlogMapper.insert(loginlog);
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
            resultVo.setRt_data(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVo;
    }

    /**
     * 账号密码登录
     *
     * @param mobile     手机号码
     * @param pwd        密码
     * @param asid       接入系统标识
     * @param clientName 接入系统名称
     * @return
     */
    public ResultVo pwdLogin(String mobile, String pwd, String asid, String clientName) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(mobile) || "".equals(pwd) || "".equals(asid) || "".equals(clientName)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        //传过来的密码采用MD5加密后截取前12位数
        Account account = accountMapper.getByMobileAndPwd(mobile, DigestUtils.md5Hex(pwd).substring(0, 12));
        if (account == null) {
            resultVo.setRt_code(Constant.RESULT_CODE_MOBILEISNOEXISTS);
            resultVo.setRt_msg(Constant.RESULT_MSG_MOBILEISNOEXISTS);
            return resultVo;
        }
        try {
            Map map = new HashMap();
            map.put("mobile", mobile);
            map.put("pwd", pwd);
            map.put("sta", new Date().getTime());//时间戳，保证每次生成的token都不一样
            String token = TokenUtils.creatToken(map, RandomUtils.GET_RANDOMSTRING(32).getBytes());
            //把token存放进redis,有效期：7天
            redisUtils.setObj(token + Constant.REDIS_KEY_LOGIN, account, 7, TimeUnit.DAYS);
            String id = RandomUtils.GET_RANDOMNUMBER(12);
            Loginlog check = loginlogMapper.getByID(id);
            if (check != null) {
                id = RandomUtils.GET_RANDOMNUMBER(12);
            }
            Loginlog loginlog = new Loginlog();
            loginlog.setLoginlogID(id);
            loginlog.setAsID(asid);
            loginlog.setClientName(clientName);
            //账户标识
            loginlog.setaID(account.getId());
            loginlog.setLoginType("3");//1.验证码 3.密码
            loginlog.setLoginTime(new Date());
            loginlog.setSerial(0);
            loginlog.setSysStatus("1");
            loginlog.setCreateTime(new Date());
            loginlog.setValueFlag("1");
            loginlogMapper.insert(loginlog);
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
            resultVo.setRt_data(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVo;
    }

    /**
     * token校验功能
     *
     * @param token token
     * @return
     */
    public ResultVo checkToken(String token) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(token)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        try {
            Account account = (Account) redisUtils.getObj(token + Constant.REDIS_KEY_LOGIN);
            if (account == null) {
                resultVo.setRt_code(Constant.RESULT_CODE_WONGTOKEN);
                resultVo.setRt_code(Constant.RESULT_MSG_WONGTOKEN);
            } else {
                //重置有效期为7天
                redisUtils.setObj(token + Constant.REDIS_KEY_LOGIN, account, 7, TimeUnit.DAYS);
                resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
                resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVo;
    }
}
