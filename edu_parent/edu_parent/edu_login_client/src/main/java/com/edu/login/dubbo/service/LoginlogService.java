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
     * @param roler      登录类型：1.教师 2.家长 3.学生 4.家长登录学生
     * @return
     */
    ResultVo codeLogin(String mobile, String code, String asid, String clientName, String roler);

    /**
     * 账号密码登录
     *
     * @param mobile     手机号码
     * @param pwd        密码
     * @param asid       接入系统标识
     * @param clientName 接入系统名称
     * @param roler      登录类型：1.教师 2.家长 3.学生 4.家长登录学生
     * @return
     */
    ResultVo pwdLogin(String mobile, String pwd, String asid, String clientName, String roler);

    /**
     * token校验功能
     *
     * @param token token
     * @return
     */
    ResultVo checkToken(String token);

    /**
     * 家长选择学生登录
     *
     * @param parentID   家长ID
     * @param studentID  学生ID
     * @param token      家长登录的token
     * @param roler      登录类型：1.教师 2.家长 3.学生 4.家长登录学生
     * @param asid       接入系统标识
     * @param clientName 接入系统名称
     * @return
     */
    ResultVo parentLoginStu(String parentID, String studentID, String token, String roler, String asid, String clientName);

    /**
     * 用户修改登录方式功能
     *
     * @param token token
     * @param type  登录类型：1.教师 2.家长 3.学生 4.家长登录学生
     * @return
     */
    public ResultVo updateLoginType(String token, String type);
}
