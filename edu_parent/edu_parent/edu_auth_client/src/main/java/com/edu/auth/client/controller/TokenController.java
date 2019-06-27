package com.edu.auth.client.controller;

import com.edu.auth.service.AuthSysCfgService;
import com.edu.base.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: edu_parent
 * @description: token相关控制器
 * @author: BaronLi
 * @create: 2019-06-27 14:31
 */
@RequestMapping("auth")
@RestController
public class TokenController {

    @Autowired
    private AuthSysCfgService authSysCfgService;

    /**
     * 签发token
     *
     * @param clientId     系统授权标识
     * @param clientSecret 系统授权秘钥
     * @param sign         签名
     * @param request
     * @return
     */
    @RequestMapping(value = "getToken", method = RequestMethod.POST)
    public ResultVo getToken(@RequestParam(value = "clientId", defaultValue = "") String clientId,
                             @RequestParam(value = "clientSecret", defaultValue = "") String clientSecret,
                             @RequestParam(value = "sign", defaultValue = "") String sign, HttpServletRequest request) {
        return authSysCfgService.getToken(clientId, clientSecret, sign, request);

    }

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "checkToken", method = RequestMethod.POST)
    public ResultVo checkToken(@RequestParam(value = "token", defaultValue = "") String token) {
        return authSysCfgService.checkToken(token);
    }
}
