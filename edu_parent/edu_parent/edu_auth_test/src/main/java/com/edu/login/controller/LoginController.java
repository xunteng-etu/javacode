package com.edu.login.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.base.ResultVo;
import com.edu.login.dubbo.service.LoginlogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: edu_parent
 * @description:
 * @author: BaronLi
 * @create: 2019-07-30 09:43
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Reference
    LoginlogService loginlogService;

    /**
     * 发送登录短信
     *
     * @param mobile
     * @param asid
     * @return
     */
    @RequestMapping(value = "sendLoginSms", method = RequestMethod.POST)
    public ResultVo sendLoginSms(@RequestParam(value = "mobile", defaultValue = "") String mobile,
                                 @RequestParam(value = "asid", defaultValue = "") String asid) {
        return loginlogService.sendLoginCode(mobile, asid);
    }

    /**
     * 验证码登录
     *
     * @param asid
     * @param clientName
     * @param mobile
     * @param code
     * @param roler
     * @return
     */
    @RequestMapping(value = "codeLogin", method = RequestMethod.POST)
    public ResultVo codeLogin(@RequestParam(value = "asid", defaultValue = "") String asid,
                              @RequestParam(value = "clientName", defaultValue = "") String clientName,
                              @RequestParam(value = "mobile", defaultValue = "") String mobile,
                              @RequestParam(value = "code", defaultValue = "") String code,
                              @RequestParam(value = "roler", defaultValue = "") String roler) {
        return loginlogService.codeLogin(mobile, code, asid, clientName, roler);
    }

    /**
     * 密码登录
     *
     * @param asid
     * @param clientName
     * @param mobile
     * @param pwd
     * @param roler
     * @return
     */
    @RequestMapping(value = "pwdLogin", method = RequestMethod.POST)
    public ResultVo pwdLogin(@RequestParam(value = "asid", defaultValue = "") String asid,
                             @RequestParam(value = "clientName", defaultValue = "") String clientName,
                             @RequestParam(value = "mobile", defaultValue = "") String mobile,
                             @RequestParam(value = "pwd", defaultValue = "") String pwd,
                             @RequestParam(value = "roler", defaultValue = "") String roler) {
        return loginlogService.pwdLogin(mobile, pwd, asid, clientName, roler);
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "checkToken", method = RequestMethod.POST)
    public ResultVo checkToken(@RequestParam(value = "token", defaultValue = "") String token) {
        return loginlogService.checkToken(token);
    }

    /**
     * 家长登录学生
     *
     * @param parentID
     * @param studentID
     * @param token
     * @param roler
     * @param asid
     * @param clientName
     * @return
     */
    @RequestMapping(value = "parentLoginStu", method = RequestMethod.POST)
    public ResultVo parentLoginStu(@RequestParam(value = "parentID", defaultValue = "") String parentID,
                                   @RequestParam(value = "studentID", defaultValue = "") String studentID,
                                   @RequestParam(value = "token", defaultValue = "") String token,
                                   @RequestParam(value = "roler", defaultValue = "") String roler,
                                   @RequestParam(value = "asid", defaultValue = "") String asid,
                                   @RequestParam(value = "clientName", defaultValue = "") String clientName) {
        return loginlogService.parentLoginStu(parentID, studentID, token, roler, asid, clientName);
    }

    /**
     * 修改登录方式
     * @param token
     * @param type
     * @return
     */
    @RequestMapping(value = "updateLoginType", method = RequestMethod.POST)
    public ResultVo updateLoginType(@RequestParam(value = "token", defaultValue = "") String token,
                                    @RequestParam(value = "type", defaultValue = "") String type) {
        return loginlogService.updateLoginType(token, type);
    }

}
