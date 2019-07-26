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

    @RequestMapping(value = "checkMobile",method = RequestMethod.POST)
    public ResultVo checkMobile(@RequestParam(value = "mobile")String mobile){
        return accountService.checkRegistered(mobile);
    }

    @RequestMapping(value = "check",method = RequestMethod.POST)
    public ResultVo check(@RequestParam(value = "mobile")String mobile,
                          @RequestParam(value = "asid")String asid,
                          @RequestParam(value = "templatecode")String templatecode,
                          @RequestParam(value = "signName")String signName){
        return accountService.sendRegister(mobile, asid, templatecode, signName);
    }


}
