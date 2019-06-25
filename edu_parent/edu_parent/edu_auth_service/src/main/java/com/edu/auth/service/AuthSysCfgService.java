package com.edu.auth.service;

import com.edu.auth.model.entity.AuthSysCfg;
import com.edu.auth.model.mapper.AuthSysCfgMapper;
import com.edu.base.Constant;
import com.edu.base.ResultVo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @program: edu_parent
 * @description: 接入系统管理服务
 * @author: BaronLi
 * @create: 2019-06-21 14:12
 */
@Service
public class AuthSysCfgService {
    @Autowired
    private AuthSysCfgMapper authSysCfgMapper;

    /**
     * 接入系统配置增加接口
     *
     * @param clientName 接入系统名称
     * @param clientIP   授权IP，允许多个用“,”逗号隔开，保存时默认“0.0.0.0”为不校验。
     * @param remark     备注
     * @return
     */
    public ResultVo save(String clientName, String clientIP, String remark) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(clientName) || "".equals(clientIP)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGSYSTEM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGSYSTEM);
            return resultVo;
        }
        String asid = RandomStringUtils.randomAlphanumeric(12);
        AuthSysCfg checkASID = authSysCfgMapper.selectByASID(asid);
        //校验asid
        if (checkASID != null) {
            asid = RandomStringUtils.randomAlphanumeric(12);
        }
        //校验clientID
        String clientID = RandomStringUtils.randomAlphanumeric(12);
        AuthSysCfg checkClientID = authSysCfgMapper.selectByClientID(clientID);
        if (checkClientID != null) {
            clientID = RandomStringUtils.randomAlphanumeric(12);
        }
        //校验clientSecret
        String clientSecret = RandomStringUtils.randomAlphanumeric(32);
        AuthSysCfg checkClientSecret = authSysCfgMapper.selectByClientSecret(clientSecret);
        if (checkClientSecret != null) {
            clientSecret = RandomStringUtils.randomAlphanumeric(32);
        }
        //校验clientKey
        String clientKey = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        //String clientKey = RandomStringUtils.randomAlphanumeric(32);
        AuthSysCfg checkClientKey = authSysCfgMapper.selectByClientKey(clientKey);
        if (checkClientKey != null) {
            clientKey = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        }
        AuthSysCfg authSysCfg = new AuthSysCfg();
        authSysCfg.setAsID(asid);
        authSysCfg.setClientName(clientName);
        authSysCfg.setClientID(clientID);
        authSysCfg.setClientSecret(clientSecret);
        authSysCfg.setClientIP(clientIP);
        authSysCfg.setRemark(remark);
        authSysCfg.setSysStatus("0");
        authSysCfg.setCreateTime(new Date());
        authSysCfg.setClientKey(clientKey);
        authSysCfg.setValueFlag("1");

        try {
            authSysCfgMapper.insert(authSysCfg);
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
            resultVo.setRt_data(authSysCfg);
            return resultVo;
        } catch (Exception e) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGSYSTEM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGSYSTEM);
            return resultVo;
        }
    }

    /**
     * 接入系统配置修改接口
     *
     * @param asID       接入系统标识
     * @param clientName 接入系统名称
     * @param clientIP   授权IP
     * @param remark     备注
     * @return
     */
    public ResultVo update(String asID, String clientName, String clientIP, String remark) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(asID) || "".equals(clientName) || "".equals(clientIP)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGSYSTEM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGSYSTEM);
            return resultVo;
        }
        try {
            authSysCfgMapper.update(clientName, clientIP, remark, new Date(), asID);
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
            return resultVo;
        } catch (Exception e) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGSYSTEM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGSYSTEM);
            return resultVo;
        }
    }

    /**
     * 接入系统配置删除接口
     *
     * @param asid 接入系统标识
     * @return
     */
    public ResultVo delete(String asid) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(asid)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGSYSTEM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGSYSTEM);
            return resultVo;
        }
        try {
            authSysCfgMapper.delete(asid);
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
            return resultVo;
        } catch (Exception e) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGSYSTEM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGSYSTEM);
            return resultVo;
        }
    }

    /**
     * 接入系统配置查询单个接口
     *
     * @param asid 接入系统标识
     * @return
     */
    public ResultVo selectByASID(String asid) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(asid)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGSYSTEM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGSYSTEM);
            return resultVo;
        }
        try {
            AuthSysCfg authSysCfg = authSysCfgMapper.selectByASID(asid);
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
            resultVo.setRt_data(authSysCfg);
            return resultVo;
        } catch (Exception e) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGSYSTEM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGSYSTEM);
            return resultVo;
        }
    }

    /**
     * 接入系统配置查询列表接口
     *
     * @param asID       接入系统标识
     * @param clientName 接入系统名称
     * @param pageNo     页码，为空时默认第一页
     * @param pageRow    一页行数，为空时默认30行
     * @return
     */
    public ResultVo findAll(String asID, String clientName, Integer pageNo, Integer pageRow) {
        ResultVo resultVo = new ResultVo();
        if (pageNo == null || pageNo - 1 <= 0) {
            pageNo = 0;
        }
        if (pageRow == null || pageRow <= 0) {
            pageRow = 30;
        }
        try {
            List<AuthSysCfg> authSysCfgs = authSysCfgMapper.findAll(asID, clientName, pageNo - 1 < 0 ? 0 : (pageNo - 1) * pageRow, pageRow);
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
            resultVo.setRt_data(authSysCfgs);
            return resultVo;
        } catch (Exception e) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGSYSTEM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGSYSTEM);
            return resultVo;
        }
    }


}
