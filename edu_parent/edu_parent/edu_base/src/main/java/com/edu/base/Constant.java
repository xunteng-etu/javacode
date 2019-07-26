package com.edu.base;

/**
 * @program: edu_parent
 * @description: 返回状态码
 * @author: BaronLi
 * @create: 2019-06-24 12:02
 */
public class Constant {
    //接口返回状态码

    /**
     * 操作失败
     */
    public static final String RESULT_CODE_WONGSYSTEM = "-1";
    /**
     * 请求成功/操作成功
     */
    public static final String RESULT_CODE_SUCCES = "0";
    /**
     * 传参错误
     */
    public static final String RESULT_CODE_WONGPARAM = "90001";
    /**
     * clientId错误或者clientSecret不属于该clientId
     */
    public static final String RESULT_CODE_WONGCLIENTID = "40001";
    /**
     * 签名校验不通过
     */
    public static final String RESULT_CODE_WONGSIGN = "40002";
    /**
     * 请求超过次数
     */
    public static final String RESULT_CODE_WONGCOUNT = "40003";
    /**
     * IP地址没有权限
     */
    public static final String RESULT_CODE_WONGIPADDRESS = "40005";
    /**
     * Token已过期
     */
    public static final String RESULT_CODE_WONGTOKEN = "40009";

    /**
     * 手机号码已存在
     */
    public static final String RESULT_CODE_MOBILEISEXISTS = "50001";


    public static final String RESULT_MSG_WONGSYSTEM = "操作失败";
    public static final String RESULT_MSG_SUCCES = "请求成功/操作成功";
    public static final String RESULT_MSG_WONGPARAM = "传参错误";
    public static final String RESULT_MSG_WONGCLIENTID = "clientId错误或者clientSecret不属于该clientId";
    public static final String RESULT_MSG_WONGSIGN = "签名校验不通过";
    public static final String RESULT_MSG_WONGIPADDRESS = "IP地址没有权限";
    public static final String RESULT_MSG_WONGTOKEN = "Token已过期";
    public static final String RESULT_MSG_WONGCOUNT = "请求超过次数";

    public static final String RESULT_MSG_MOBILEISEXISTS="手机号码已存在";
}
