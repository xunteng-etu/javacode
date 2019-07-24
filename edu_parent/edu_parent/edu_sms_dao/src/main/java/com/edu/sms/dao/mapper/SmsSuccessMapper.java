package com.edu.sms.dao.mapper;

import com.edu.sms.model.entity.SmsBase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SmsSuccessMapper {

    /**
     * 新增
     *
     * @param smsBase
     */
    void insert(SmsBase smsBase);

    /**
     * 根据ID查询
     *
     * @param sid
     * @return
     */
    SmsBase selectBySid(@Param("sid") String sid);
}
