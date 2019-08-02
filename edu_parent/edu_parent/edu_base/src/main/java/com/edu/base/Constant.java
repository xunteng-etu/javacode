package com.edu.base;

/**
 * @program: edu_parent
 * @description: 返回状态码
 * @author: BaronLi
 * @create: 2019-06-24 12:02
 */
public class Constant {

    //阿里云短信平台相关参数
    /**
     * 地区标识（华南一区）
     */
    public static final String regionId = "cn-shenzhen";
    /**
     * accessKeyId
     */
    public static final String accessKeyId = "LTAINlLcVcUIsmk2";
    /**
     * accessSecret
     */
    public static final String accessSecret = "yWVRl4DtNx228fy9pwLpdUJh9xRdbp";
    /**
     * 访问地址
     */
    public static final String sendSmsAddress = "dysmsapi.aliyuncs.com";
    /**
     * 版本号
     */
    public static final String sendSmsVersion = "2017-05-25";
    /**
     * 接口名称
     */
    public static final String sendSmsAction = "SendSms";
    /**
     * 签名名称
     */
    public static final String signName = "WeLearning";
    /**
     * 用户注册验证码模板ID
     */
    public static final String register_code = "SMS_170420060";
    /**
     * 登录确认验证码
     */
    public static final String login_code = "SMS_170420062";

    //redis后缀
    /**
     * 账号注册验证码存放在redis-key的后缀
     */
    public static final String REDIS_KEY_REGISTER_CODE = "_ResCode";

    /**
     * 账号登录验证码存放在redis-key的后缀
     */
    public static final String REDIS_KEY_LOGIN_CODE = "_LoginCode";

    /**
     * 账号登录生成token存放在redis-key的后缀
     */
    public static final String REDIS_KEY_LOGIN = "_Login";

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
    /**
     * 手机号码不存在
     */
    public static final String RESULT_CODE_MOBILEISNOEXISTS = "50003";

    /**
     * 验证码错误
     */
    public static final String RESULT_CODE_WONGCODE = "50002";
    /**
     * 学生已注册
     */
    public static final String RESULT_CODE_STUISREGISTER = "60001";

    /**
     * 学生与该家长已绑定
     */
    public static final String RESULT_CODE_WONGSTUBING = "60002";


    //接口返回消息
    public static final String RESULT_MSG_WONGSYSTEM = "操作失败";
    public static final String RESULT_MSG_SUCCES = "请求成功/操作成功";
    public static final String RESULT_MSG_WONGPARAM = "传参错误";
    public static final String RESULT_MSG_WONGCLIENTID = "clientId错误或者clientSecret不属于该clientId";
    public static final String RESULT_MSG_WONGSIGN = "签名校验不通过";
    public static final String RESULT_MSG_WONGIPADDRESS = "IP地址没有权限";
    public static final String RESULT_MSG_WONGTOKEN = "Token已过期";
    public static final String RESULT_MSG_WONGCOUNT = "请求超过次数";
    public static final String RESULT_MSG_MOBILEISEXISTS = "手机号码已存在";
    public static final String RESULT_MSG_MOBILEISNOEXISTS = "手机号码不存在";
    public static final String RESULT_MSG_WONGCODE = "验证码错误";
    public static final String RESULT_MSG_STUISREGISTER = "学生已注册";
    public static final String RESULT_MSG_WONGSTUBING = "学生与该家长已绑定";
}
