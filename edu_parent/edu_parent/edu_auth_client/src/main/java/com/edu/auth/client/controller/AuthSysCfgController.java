package com.edu.auth.client.controller;

import com.edu.auth.service.AuthSysCfgService;
import com.edu.base.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: edu_parent
 * @description:接入系统配置控制器
 * @author: BaronLi
 * @create: 2019-06-25 11:16
 */
@RestController
@RequestMapping("syscfg")
public class AuthSysCfgController {

    @Autowired
    private AuthSysCfgService authSysCfgService;

    /**
     * 接入系统配置增加接口
     *
     * @param clientName 接入系统名称
     * @param clientIP   授权IP
     * @param remark     备注
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultVo save(@RequestParam(name = "clientName", defaultValue = "") String clientName,
                         @RequestParam(name = "clientIP", defaultValue = "") String clientIP,
                         @RequestParam(name = "remark", defaultValue = "") String remark) {

        return authSysCfgService.save(clientName, clientIP, remark);
    }

    /**
     * 接入系统配置修改接口
     *
     * @param asId 接入系统标识
     * @param clientName 接入系统名称
     * @param clientIP 授权IP
     * @param remark 备注
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResultVo update(@RequestParam(name = "asId", defaultValue = "") String asId,
                           @RequestParam(name = "clientName", defaultValue = "") String clientName,
                           @RequestParam(name = "clientIP", defaultValue = "") String clientIP,
                           @RequestParam(name = "remark", defaultValue = "") String remark) {

        return authSysCfgService.update(asId, clientName, clientIP, remark);
    }

    /**
     * 5)	接入系统配置删除接口
     * @param asId 接入系统标识
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResultVo delete(@RequestParam(value = "asId",defaultValue = "")String asId){
        return authSysCfgService.delete(asId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResultVo get(@RequestParam(value = "asId",defaultValue = "")String asId){
        return authSysCfgService.selectByASID(asId);
    }

}
