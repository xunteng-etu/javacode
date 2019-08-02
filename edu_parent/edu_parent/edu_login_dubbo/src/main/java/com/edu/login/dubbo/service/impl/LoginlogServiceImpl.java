package com.edu.login.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.edu.base.ResultVo;
import com.edu.login.dubbo.service.LoginlogService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: edu_parent
 * @description:
 * @author: BaronLi
 * @create: 2019-07-30 09:32
 */
@Service(timeout = 5000, retries = -1)
public class LoginlogServiceImpl implements LoginlogService {
    @Autowired
    private com.edu.account.service.LoginlogService loginlogService;

    public ResultVo sendLoginCode(String mobile, String asid) {
        return loginlogService.sendLoginCode(mobile, asid);
    }

    public ResultVo codeLogin(String mobile, String code, String asid, String clientName, String roler) {
        return loginlogService.codeLogin(mobile, code, asid, clientName, roler);
    }

    public ResultVo pwdLogin(String mobile, String pwd, String asid, String clientName, String roler) {
        return loginlogService.pwdLogin(mobile, pwd, asid, clientName, roler);
    }

    public ResultVo checkToken(String token) {
        return loginlogService.checkToken(token);
    }

    public ResultVo parentLoginStu(String parentID, String studentID, String token, String roler, String asid, String clientName) {
        return loginlogService.parentLoginStu(parentID, studentID, token, roler, asid, clientName);
    }

    public ResultVo updateLoginType(String token, String type) {
        return loginlogService.updateLoginType(token, type);
    }
}
