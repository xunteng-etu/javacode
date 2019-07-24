package com.edu.sms.dao.mapper;

import com.edu.sms.model.entity.SmsBase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SmsWaitMapper {

    /**
     * 新增
     *
     * @param smsBase 短信模板实体
     */
    void insert(SmsBase smsBase);

    /**
     * 根据ID查询
     *
     * @param sid 短信模板ID
     * @return
     */
    SmsBase selectBySid(@Param("sid") String sid);

    /**
     * 修改短信状态
     *
     * @param sid       短信模板ID
     * @param sysStatus 需要更改的状态
     */
    void updateSysStatus(@Param("sid") String sid, @Param("sysStatus") String sysStatus);

    /**
     * 根据状态查询数据
     *
     * @param sysStatus 需要查询的状态
     * @param count     需要查询的条数
     * @return
     */
    List<SmsBase> selectBySysStatus(@Param("sysStatus") String sysStatus, @Param("count") Integer count);

    void delete(@Param("sid")String sid);
}
