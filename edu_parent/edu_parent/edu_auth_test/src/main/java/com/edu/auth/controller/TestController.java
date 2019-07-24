package com.edu.auth.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.base.ResultVo;
import com.edu.auth.dubbo.service.AuthSysCfgService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: edu_parent
 * @description:
 * @author: BaronLi
 * @create: 2019-07-22 12:13
 */
@RestController
@RequestMapping("dubbo")
public class TestController {
    @Reference
    private AuthSysCfgService authSysCfgService;

    @RequestMapping("test")
    public ResultVo test(@RequestParam(value = "asid") String asid) {
        return authSysCfgService.selectByASID(asid);
    }
}
