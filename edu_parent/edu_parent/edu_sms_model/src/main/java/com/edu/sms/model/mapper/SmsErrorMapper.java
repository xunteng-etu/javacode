package com.edu.sms.model.mapper;

import com.edu.sms.model.entity.SmsError;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SmsErrorMapper {

    void insert(SmsError smsError);

    SmsError selectBySid(@Param("sid")String sid);
}
