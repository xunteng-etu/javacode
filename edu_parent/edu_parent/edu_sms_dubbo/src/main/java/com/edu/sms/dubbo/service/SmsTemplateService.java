package com.edu.sms.dubbo.service;

import com.edu.base.ResultVo;

public interface SmsTemplateService {

    /**
     * 新增短信发送模板
     *
     * @param code    短信平台模板编号
     * @param details 短信模板内容
     * @param remark  备注
     * @return
     */
    ResultVo add(String code, String details, String remark);

    /**
     * 修改短信发送模板
     *
     * @param stid    短信模板标识
     * @param code    短信平台模板编号
     * @param details 短信模板内容
     * @param remark  备注
     * @return
     */
    ResultVo update(String stid, String code, String details, String remark);

    /**
     * 删除短信发送模板（逻辑删除）
     *
     * @param stid 短信模板标识
     * @return
     */
    ResultVo delete(String stid);

    /**
     * 根据STID查询短信模板
     *
     * @param stid
     * @return
     */
    ResultVo selectBySTID(String stid);

    /**
     * 分页查询所有
     *
     * @param stid    短信模板标识
     * @param code    短信平台模板编号
     * @param pageNo  页码，为空时默认第一页
     * @param pageRow 一页行数，为空时默认30行
     * @return
     */
    ResultVo findAll(String stid, String code, Integer pageNo, Integer pageRow);
}
