package com.edu.sms.service;

import com.edu.base.Constant;
import com.edu.base.ResultVo;
import com.edu.sms.model.entity.SmsTemplate;
import com.edu.sms.dao.mapper.SmsTemplateMapper;
import com.edu.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: edu_parent
 * @description: 短信发送模板业务层
 * @author: BaronLi
 * @create: 2019-07-08 15:06
 */
@Service
public class SmsTemplateService {
    @Autowired
    private SmsTemplateMapper smsTemplateMapper;

    /**
     * 新增短信发送模板
     *
     * @param code    短信平台模板编号
     * @param details 短信模板内容
     * @param remark  备注
     * @return
     */
    public ResultVo add(String code, String details, String remark) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(code) || "".equals(details)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        String stid = RandomUtils.GET_RANDOMSTRING(12);
        SmsTemplate smsTemplate = new SmsTemplate();
        smsTemplate.setStid(stid);
        smsTemplate.setCode(code);
        smsTemplate.setDetails(details);
        smsTemplate.setRemark(remark);
        smsTemplate.setSerial(0);
        smsTemplate.setSysStatus("0");
        smsTemplate.setCreateTime(new Date());
        smsTemplate.setValueFlag("1");

        try {
            smsTemplateMapper.insert(smsTemplate);
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
            resultVo.setRt_data(smsTemplate);
            return resultVo;
        } catch (Exception e) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGSYSTEM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGSYSTEM);
            return resultVo;
        }
    }

    /**
     * 修改短信发送模板
     *
     * @param stid    短信模板标识
     * @param code    短信平台模板编号
     * @param details 短信模板内容
     * @param remark  备注
     * @return
     */
    public ResultVo update(String stid, String code, String details, String remark) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(stid) || "".equals(code) || "".equals(details)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        try {
            smsTemplateMapper.update(stid, code, details, remark, new Date());
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
     * 删除短信发送模板（逻辑删除）
     *
     * @param stid 短信模板标识
     * @return
     */
    public ResultVo delete(String stid) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(stid)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        try {
            smsTemplateMapper.delete(stid);
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
     * 根据STID查询短信模板
     *
     * @param stid
     * @return
     */
    public ResultVo selectBySTID(String stid) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(stid)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        try {
            SmsTemplate smsTemplate = smsTemplateMapper.selectBySTID(stid);
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
            resultVo.setRt_data(smsTemplate);
            return resultVo;
        } catch (Exception e) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGSYSTEM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGSYSTEM);
            return resultVo;
        }
    }

    /**
     * 分页查询所有
     *
     * @param stid    短信模板标识
     * @param code    短信平台模板编号
     * @param pageNo  页码，为空时默认第一页
     * @param pageRow 一页行数，为空时默认30行
     * @return
     */
    public ResultVo findAll(String stid, String code, Integer pageNo, Integer pageRow) {
        ResultVo resultVo = new ResultVo();
        if (pageNo == null) {
            pageNo = 0;
        }
        if (pageRow == null || pageRow <= 0) {
            pageRow = 30;
        }
        try {
            List<SmsTemplate> smsTemplates = smsTemplateMapper.findAll(stid, code, pageNo - 1 < 0 ? 0 : (pageNo - 1) * pageRow, pageRow);
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
            resultVo.setRt_data(smsTemplates);
            return resultVo;
        } catch (Exception e) {
            return resultVo;
        }
    }

}
