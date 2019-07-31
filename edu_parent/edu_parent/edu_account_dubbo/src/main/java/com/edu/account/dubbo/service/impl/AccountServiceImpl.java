package com.edu.account.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.edu.account.dubbo.service.AccountService;
import com.edu.base.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: edu_parent
 * @description:
 * @author: BaronLi
 * @create: 2019-07-25 17:36
 */
@Service(timeout = 5000,retries = -1)
public class AccountServiceImpl implements AccountService {

    @Autowired
    private com.edu.account.service.AccountService accountService;

    public ResultVo checkRegistered(String mobile) {
        return accountService.checkRegistered(mobile);
    }

    public ResultVo createPwd(String id, String pwd) {
        return accountService.createPwd(id, pwd);
    }

    public ResultVo sendRegister(String mobile, String asid, String templatecode, String signName) {
        return accountService.sendRegister(mobile, asid, templatecode, signName);
    }

    public ResultVo register(String mobile, String code, String roler) {
        return accountService.register(mobile, code, roler);
    }
}
