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

    @RequestMapping(value = "sendLoginSms", method = RequestMethod.POST)
    public ResultVo sendLoginSms(@RequestParam(value = "mobile", defaultValue = "") String mobile,
                                 @RequestParam(value = "asid", defaultValue = "") String asid) {
        return loginlogService.sendLoginCode(mobile, asid);
    }

    @RequestMapping(value = "codeLogin", method = RequestMethod.POST)
    public ResultVo codeLogin(@RequestParam(value = "asid", defaultValue = "") String asid,
                              @RequestParam(value = "clientName", defaultValue = "") String clientName,
                              @RequestParam(value = "mobile", defaultValue = "") String mobile,
                              @RequestParam(value = "code", defaultValue = "") String code) {
        return loginlogService.codeLogin(mobile, code, asid, clientName);
    }

    @RequestMapping(value = "pwdLogin", method = RequestMethod.POST)
    public ResultVo pwdLogin(@RequestParam(value = "asid", defaultValue = "") String asid,
                             @RequestParam(value = "clientName", defaultValue = "") String clientName,
                             @RequestParam(value = "mobile", defaultValue = "") String mobile,
                             @RequestParam(value = "pwd", defaultValue = "") String pwd) {
        return loginlogService.pwdLogin(mobile, pwd, asid, clientName);
    }

    @RequestMapping(value = "checkToken",method = RequestMethod.POST)
    public ResultVo checkToken(@RequestParam(value = "token", defaultValue = "") String token) {
        return loginlogService.checkToken(token);
    }
}
