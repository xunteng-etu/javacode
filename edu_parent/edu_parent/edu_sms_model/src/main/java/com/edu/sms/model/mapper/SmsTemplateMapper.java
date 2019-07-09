package com.edu.sms.model.mapper;

import com.edu.sms.model.entity.SmsTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Mapper
@Repository
public interface SmsTemplateMapper {

    void insert(SmsTemplate smsTemplate);

    void update(@Param("stid") String stid, @Param("code") String code,
                @Param("details") String details, @Param("remark") String remark,
                @Param("updateTime")Date updateTime);

    void delete(@Param("stid") String stid);

    SmsTemplate selectBySTID(@Param("stid") String stid);
}
