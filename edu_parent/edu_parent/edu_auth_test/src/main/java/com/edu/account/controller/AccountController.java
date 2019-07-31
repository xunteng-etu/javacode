package com.edu.account.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.account.dubbo.service.AccountService;
import com.edu.base.ResultVo;
import com.edu.utils.RedisUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: edu_parent
 * @description:
 * @author: BaronLi
 * @create: 2019-07-26 09:50
 */
@RestController
@RequestMapping("account")
public class AccountController {

    @Reference
    AccountService accountService;

    /**
     * 查询手机号是否已注册
     * @param mobile
     * @return
     */
    @RequestMapping(value = "checkMobile", method = RequestMethod.POST)
    public ResultVo checkMobile(@RequestParam(value = "mobile", defaultValue = "") String mobile) {
        return accountService.checkRegistered(mobile);
    }

    /**
     * 手机注册验证码
     * @param mobile
     * @param asid
     * @param templatecode
     * @param signName
     * @return
     */
    @RequestMapping(value = "check", method = RequestMethod.POST)
    public ResultVo check(@RequestParam(value = "mobile", defaultValue = "") String mobile,
                          @RequestParam(value = "asid", defaultValue = "") String asid,
                          @RequestParam(value = "templatecode", defaultValue = "") String templatecode,
                          @RequestParam(value = "signName", defaultValue = "") String signName) {
        return accountService.sendRegister(mobile, asid, templatecode, signName);
    }

    /**
     * 用户注册
     * @param mobile
     * @param code
     * @param roler
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResultVo register(@RequestParam(value = "mobile",defaultValue = "")String mobile,
                             @RequestParam(value = "code",defaultValue = "")String code,
                             @RequestParam(value = "roler",defaultValue = "")String roler) {
        return accountService.register(mobile,code,roler);

    }

    @RequestMapping(value = "createPwd",method = RequestMethod.POST)
    public ResultVo createPwd(@RequestParam(value = "id",defaultValue = "")String id,
                              @RequestParam(value = "pwd",defaultValue = "")String pwd){
        return accountService.createPwd(id,pwd);
    }

}
