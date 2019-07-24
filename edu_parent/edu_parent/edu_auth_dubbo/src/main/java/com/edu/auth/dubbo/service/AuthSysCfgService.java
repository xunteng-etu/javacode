package com.edu.auth.dubbo.service;

import com.edu.base.ResultVo;

import javax.servlet.http.HttpServletRequest;

public interface AuthSysCfgService {
    /**
     * 接入系统配置增加接口
     *
     * @param clientName 接入系统名称
     * @param clientIP   授权IP，允许多个用“,”逗号隔开，保存时默认“0.0.0.0”为不校验。
     * @param remark     备注
     * @return
     */
    ResultVo save(String clientName, String clientIP, String remark);

    /**
     * 接入系统配置修改接口
     *
     * @param asID       接入系统标识
     * @param clientName 接入系统名称
     * @param clientIP   授权IP
     * @param remark     备注
     * @return
     */
    ResultVo update(String asID, String clientName, String clientIP, String remark);

    /**
     * 接入系统配置删除接口
     *
     * @param asid 接入系统标识
     * @return
     */
    ResultVo delete(String asid);

    /**
     * 接入系统配置查询单个接口
     *
     * @param asid 接入系统标识
     * @return
     */
    ResultVo selectByASID(String asid);

    /**
     * 接入系统配置查询列表接口
     *
     * @param asID       接入系统标识
     * @param clientName 接入系统名称
     * @param pageNo     页码，为空时默认第一页
     * @param pageRow    一页行数，为空时默认30行
     * @return
     */
    ResultVo findAll(String asID, String clientName, Integer pageNo, Integer pageRow);

    /**
     * 获取token
     *
     * @param clientId     clientId
     * @param clientSecret clientSecret
     * @param sign         sign
     * @param ip           ip
     * @return
     */
    ResultVo getToken(String clientId, String clientSecret, String sign, String ip);

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    ResultVo checkToken(String token);

}
