package com.edu.auth.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.auth.dubbo.service.AuthSysCfgService;
import com.edu.base.ResultVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: edu_parent
 * @description:接入系统配置控制器
 * @author: BaronLi
 * @create: 2019-06-25 11:16
 */
@RestController
@RequestMapping("syscfg")
public class AuthSysCfgController {

    @Reference
    private AuthSysCfgService authSysCfgService;

    /**
     * 接入系统配置修改/增加接口
     *
     * @param asId       接入系统标识-为空时新增，不为空时修改
     * @param clientName 接入系统名称
     * @param clientIP   授权IP
     * @param remark     备注
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResultVo update(@RequestParam(name = "asId", defaultValue = "") String asId,
                           @RequestParam(name = "clientName", defaultValue = "") String clientName,
                           @RequestParam(name = "clientIP", defaultValue = "") String clientIP,
                           @RequestParam(name = "remark", defaultValue = "") String remark) {
        if (!"".equals(asId)) {
            return authSysCfgService.update(asId, clientName, clientIP, remark);
        } else
            return authSysCfgService.save(clientName, clientIP, remark);
    }

    /**
     * 接入系统配置删除接口
     *
     * @param asId 接入系统标识
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResultVo delete(@RequestParam(value = "asId", defaultValue = "") String asId) {
        return authSysCfgService.delete(asId);
    }

    /**
     * 接入系统配置查询单个
     *
     * @param asId 接入系统标识
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultVo get(@RequestParam(value = "asId", defaultValue = "") String asId) {
        return authSysCfgService.selectByASID(asId);
    }

    /**
     * 接入系统配置查询列表
     *
     * @param asID       接入系统标识
     * @param clientName 接入系统名称
     * @param pageNo     页码，为空时默认第一页
     * @param pageRow    一页行数，为空时默认30行
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultVo findAll(@RequestParam(value = "asID", defaultValue = "") String asID,
                            @RequestParam(value = "clientName", defaultValue = "") String clientName,
                            @RequestParam(value = "pageNo", defaultValue = "") Integer pageNo,
                            @RequestParam(value = "pageRow", defaultValue = "") Integer pageRow) {
        return authSysCfgService.findAll(asID, clientName, pageNo, pageRow);

    }

}
