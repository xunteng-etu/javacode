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

    /**
     * 用户注册
     *
     * @param mobile 注册手机
     * @param code   验证码
     * @param roler  角色（1.教师 2.学生 3.家长）
     * @return
     */
    ResultVo register(String mobile, String code, String roler);

    /**
     * 家长注册学生服务
     *
     * @param stuNum 学籍号
     * @param name   学生姓名
     * @param sex    性别
     * @param date   出生日期
     * @param rel    家长与该学生关系：（1：母，2：父，3：祖母，4：祖父，5：外婆：6：外公，9：其他亲戚）
     * @param token  家长登录返回token，用于判断登录信息是否失效
     * @return
     */
    ResultVo parentRegister(String stuNum, String name, String sex, String date, String rel, String token);


}
