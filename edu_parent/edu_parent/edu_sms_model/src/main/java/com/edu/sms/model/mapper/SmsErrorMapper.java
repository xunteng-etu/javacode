package com.edu.sms.model.mapper;

import com.edu.sms.model.entity.SmsError;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SmsErrorMapper {

    /**
     * 新增
     *
     * @param smsError
     */
    void insert(SmsError smsError);

    /**
     * 根据ID查询
     *
     * @param sid
     * @return
     */
    SmsError selectBySid(@Param("sid") String sid);

    /**
     * 根据状态查询数据
     *
     * @param sysStatus 需要查询的状态
     * @param count     需要查询的条数
     * @return
     */
    List<SmsError> selectBySysStatus(@Param("sysStatus") String sysStatus, @Param("count") Integer count);

    /**
     * 根据短信标识修改状态
     * @param sysStatus 需要修改的状态
     * @param sid 短信密钥
     */
    void updateSysStatus(@Param("sysStatus")String sysStatus,@Param("sid") String sid);

    /**
     * 根据密钥删除
     * @param sid 短信密钥
     */
    void delete(@Param("sid")String sid);

    /**
     * 根据短信标识修改失败次数
     * @param countFail 失败次数
     * @param sid 短信标识
     */
    void updateCountFail(@Param("countFail")Integer countFail,@Param("sid")String sid);
}
