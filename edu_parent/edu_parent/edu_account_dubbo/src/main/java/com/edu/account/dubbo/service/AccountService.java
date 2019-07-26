package com.edu.account.dubbo.service;

import com.edu.base.ResultVo;

public interface AccountService {
    /**
     * 查询手机号是否已注册
     *
     * @param mobile 手机号
     * @return
     */
    ResultVo checkRegistered(String mobile);

    /**
     * 创建密码服务
     *
     * @param id  账号ID
     * @param pwd 密码
     * @return
     */
    ResultVo createPwd(String id, String pwd);


    /**
     * 手机注册验证码
     *
     * @param mobile       手机号码
     * @param asid         接入系统标识
     * @param templatecode 短信模板编号
     * @param signName     短信签名
     * @return
     */
    ResultVo sendRegister(String mobile, String asid, String templatecode, String signName);
}
