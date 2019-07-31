package com.edu.login.dubbo.service;

import com.edu.base.ResultVo;

public interface LoginlogService {
    /**
     * 用户手机登录验证码发送功能
     *
     * @param mobile 手机号码
     * @param asid   接入系统标识
     * @return
     */
    ResultVo sendLoginCode(String mobile, String asid);

    /**
     * 短信验证码登录
     *
     * @param mobile     手机号码
     * @param code       验证码
     * @param asid       接入系统标识
     * @param clientName 接入系统名称
     * @return
     */
    ResultVo codeLogin(String mobile, String code, String asid, String clientName);

    /**
     * 账号密码登录
     *
     * @param mobile     手机号码
     * @param pwd        密码
     * @param asid       接入系统标识
     * @param clientName 接入系统名称
     * @return
     */
    ResultVo pwdLogin(String mobile, String pwd, String asid, String clientName);

    /**
     * token校验功能
     *
     * @param token token
     * @return
     */
    ResultVo checkToken(String token);
}
