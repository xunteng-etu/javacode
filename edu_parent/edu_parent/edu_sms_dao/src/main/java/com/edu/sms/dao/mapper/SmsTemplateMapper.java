package com.edu.sms.dao.mapper;

import com.edu.sms.model.entity.SmsTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface SmsTemplateMapper {

    /**
     * 新增
     *
     * @param smsTemplate
     */
    void insert(SmsTemplate smsTemplate);

    /**
     * 修改
     *
     * @param stid       短信模板标识
     * @param code       短信模板编号
     * @param details    短信模板内容
     * @param remark     备注
     * @param updateTime 修改时间
     */
    void update(@Param("stid") String stid, @Param("code") String code,
                @Param("details") String details, @Param("remark") String remark,
                @Param("updateTime") Date updateTime);

    /**
     * 删除（逻辑删除）
     *
     * @param stid
     */
    void delete(@Param("stid") String stid);

    /**
     * 根据ID查询对象
     *
     * @param stid
     * @return
     */
    SmsTemplate selectBySTID(@Param("stid") String stid);

    /**
     * 分页查询
     *
     * @param stid    短信模板标识
     * @param code    短信模板编号
     * @param pageNo  页码，为空时默认第一页
     * @param pageRow 一页行数，为空时默认30行
     * @return
     */
    List<SmsTemplate> findAll(@Param("stid") String stid, @Param("code") String code,
                              @Param("pageNo") Integer pageNo, @Param("pageRow") Integer pageRow);
}
