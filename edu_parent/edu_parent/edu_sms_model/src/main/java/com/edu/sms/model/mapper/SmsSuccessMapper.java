package com.edu.sms.model.mapper;

import com.edu.sms.model.entity.SmsBase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SmsSuccessMapper {

    void insert(SmsBase smsBase);

    SmsBase selectBySid(@Param("sid") String sid);
}
