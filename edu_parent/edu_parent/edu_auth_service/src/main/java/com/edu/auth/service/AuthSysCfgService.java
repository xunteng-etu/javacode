package com.edu.auth.service;

import com.edu.auth.model.entity.AuthSysCfg;
import com.edu.auth.model.entity.AuthSysLog;
import com.edu.auth.dao.mapper.AuthSysCfgMapper;
import com.edu.auth.dao.mapper.AuthSysLogMapper;
import com.edu.base.Constant;
import com.edu.base.ResultVo;
import com.edu.utils.*;
import com.nimbusds.jose.JOSEException;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private AuthSysLogMapper authSysLogMapper;

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
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        String asid = RandomUtils.GET_ASID();
        AuthSysCfg checkASID = authSysCfgMapper.selectByASID(asid);
        //校验asid
        if (checkASID != null) {
            asid = RandomUtils.GET_ASID();
        }
        //校验clientID
        String clientID = RandomUtils.GET_CLIENT_ID();
        AuthSysCfg checkClientID = authSysCfgMapper.selectByClientID(clientID);
        if (checkClientID != null) {
            clientID = RandomUtils.GET_CLIENT_ID();
        }
        //校验clientSecret
        String clientSecret = RandomUtils.GET_CLIENT_SECRET();
        AuthSysCfg checkClientSecret = authSysCfgMapper.selectByClientSecret(clientSecret);
        if (checkClientSecret != null) {
            clientSecret = RandomUtils.GET_CLIENT_SECRET();
        }
        //校验clientKey
        String clientKey = RandomUtils.GET_CLIENT_KEY();
        AuthSysCfg checkClientKey = authSysCfgMapper.selectByClientKey(clientKey);
        if (checkClientKey != null) {
            clientKey = RandomUtils.GET_CLIENT_KEY();
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
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        try {
            authSysCfgMapper.update(clientName, clientIP, remark, new Date(), asID);
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
            return resultVo;
        } catch (Exception e) {
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
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        try {
            authSysCfgMapper.delete(asid);
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
            return resultVo;
        } catch (Exception e) {
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
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        try {
            AuthSysCfg authSysCfg = authSysCfgMapper.selectByASID(asid);
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
            resultVo.setRt_data(authSysCfg);
            return resultVo;
        } catch (Exception e) {
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
        if (pageNo == null) {
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
            return resultVo;
        }
    }

    /**
     * 获取token
     *
     * @param clientId     clientId
     * @param clientSecret clientSecret
     * @param sign         sign
     * @param request
     * @return
     */
    public ResultVo getToken(String clientId, String clientSecret, String sign, HttpServletRequest request) {
        //请求参数
        Map<String, Object> logMap = new HashMap<String, Object>();
        logMap.put("clientId", clientId);
        logMap.put("clientSecret", clientSecret);
        logMap.put("sign", sign);

        AuthSysLog log = new AuthSysLog();
        String asl_id = RandomUtils.GET_ASID();
        //唯一校验
        AuthSysLog authSysLog = authSysLogMapper.selectByASL_ID(asl_id);
        if(authSysLog != null){
            asl_id = RandomUtils.GET_ASID();
        }
        log.setAslID(asl_id);
        log.setRequestTime(new Date());
        log.setRequestIP(IpUtils.getIpAddress(request));
        log.setRequestParm(logMap.toString());
        log.setSerial(0);
        log.setSysStatus("0");
        log.setCreateTime(new Date());
        log.setValueFlag("1");


        ResultVo resultVo = new ResultVo();
        if ("".equals(clientId) || "".equals(clientSecret) || "".equals(sign)) {
            log.setRemark("非法请求参数");
            log.setIsAuth("0");
            authSysLogMapper.insert(log);

            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        //校验参数是否有效
        AuthSysCfg authSysCfg = authSysCfgMapper.selectByClientIdAndClientSecret(clientId, clientSecret);
        if (authSysCfg == null) {
            log.setRemark("非法请求参数");
            log.setIsAuth("0");
            authSysLogMapper.insert(log);
            resultVo.setRt_code(Constant.RESULT_CODE_WONGCLIENTID);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGCLIENTID);
            return resultVo;
        }
        log.setAsID(authSysCfg.getAsID());

        //签名
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("client_id", clientId);
        map.put("client_secret", clientSecret);
        String checkSign = SignUtils.getSign(map, authSysCfg.getClientKey());
        if (!sign.equals(checkSign)) {
            log.setRemark("签名失败");
            log.setIsAuth("0");
            authSysLogMapper.insert(log);
            resultVo.setRt_code(Constant.RESULT_CODE_WONGSIGN);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGSIGN);
            return resultVo;
        }
        //请求的IP地址
        String requestIP = IpUtils.getIpAddress(request);
        //校验IP是否有效
        if (!ArrayUtils.contains(authSysCfg.getClientIP().split(","), requestIP)) {
            log.setRemark("非法IP地址");
            log.setIsAuth("0");
            authSysLogMapper.insert(log);
            resultVo.setRt_code(Constant.RESULT_CODE_WONGIPADDRESS);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGIPADDRESS);
            return resultVo;
        }
        //次数限制
        Map<String, Object> redisMap = (Map) redisUtils.getObj(authSysCfg.getAsID() + DateUtils.getNowShort());
        if (redisMap != null) {
            Integer count = (Integer) redisMap.get("count");
            if (count > 20) {
                log.setRemark("超过次数限制");
                log.setIsAuth("0");
                authSysLogMapper.insert(log);
                resultVo.setRt_code(Constant.RESULT_CODE_WONGCOUNT);
                resultVo.setRt_msg(Constant.RESULT_MSG_WONGCOUNT);
                return resultVo;
            }
        }
        //生成token
        try {
            map.put("sta", new Date().getTime());//时间戳，保证每次生成的token都不一样
            String token = TokenUtils.creatToken(map, authSysCfg.getClientKey().getBytes());
            //缓存到redis
            //1、token，key：token,value:接入系统信息，8小时有效
            redisUtils.setObj(token, authSysCfg, 8, TimeUnit.HOURS);
            //2、缓存接入系统信息，key:AS_ID+YYYYMMDD,value：接入系统信息+当天接入次数，24小时有效，每次更新次数都重新计时。
            if (redisMap == null) {
                redisMap = new HashMap<String, Object>();
                redisMap.put("count", 1);
            } else {
                Integer count = (Integer) redisMap.get("count");
                redisMap.put("count", count + 1);
            }
            redisMap.put("data", authSysCfg);
            redisUtils.setObj(authSysCfg.getAsID() + DateUtils.getNowShort(), redisMap, 24, TimeUnit.HOURS);
            //保存到数据库
            log.setMkToken(token);
            log.setIsAuth("1");
            authSysLogMapper.insert(log);
            //返回信息
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
            resultVo.setRt_data(token);
            return resultVo;
            //结束
        } catch (JOSEException e) {
            return resultVo;
        }
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    public ResultVo checkToken(String token) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(token)) {
            return resultVo;
        }
        AuthSysCfg authSysCfg = (AuthSysCfg) redisUtils.getObj(token);
        if (authSysCfg != null) {
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
            resultVo.setRt_data(authSysCfg);
            return resultVo;
        }else {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGTOKEN);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGTOKEN);
        }
        return resultVo;
    }
}
