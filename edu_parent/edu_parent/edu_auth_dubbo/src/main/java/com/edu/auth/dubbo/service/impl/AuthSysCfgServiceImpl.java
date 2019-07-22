package com.edu.auth.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.edu.auth.dubbo.service.AuthSysCfgService;
import com.edu.base.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: edu_parent
 * @description:
 * @author: BaronLi
 * @create: 2019-07-22 10:12
 */
@Service
public class AuthSysCfgServiceImpl implements AuthSysCfgService {

    @Autowired
    private com.edu.auth.service.AuthSysCfgService authSysCfgService;

    public ResultVo save(String clientName, String clientIP, String remark) {
        return authSysCfgService.save(clientName, clientIP, remark);
    }

    public ResultVo update(String asID, String clientName, String clientIP, String remark) {
        return authSysCfgService.update(asID, clientName, clientIP, remark);
    }

    public ResultVo delete(String asid) {
        return authSysCfgService.delete(asid);
    }

    public ResultVo selectByASID(String asid) {
        return authSysCfgService.selectByASID(asid);
    }

    public ResultVo findAll(String asID, String clientName, Integer pageNo, Integer pageRow) {
        return authSysCfgService.findAll(asID, clientName, pageNo, pageRow);
    }

    public ResultVo getToken(String clientId, String clientSecret, String sign, HttpServletRequest request) {
        return authSysCfgService.getToken(clientId, clientSecret, sign, request);
    }

    public ResultVo checkToken(String token) {
        return authSysCfgService.checkToken(token);
    }
}
