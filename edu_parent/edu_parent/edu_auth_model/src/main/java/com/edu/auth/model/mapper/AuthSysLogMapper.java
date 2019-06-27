package com.edu.auth.model.mapper;

import com.edu.auth.model.entity.AuthSysLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthSysLogMapper {
    /**
     * 新增
     *
     * @param authSysLog
     * @return
     */
    void insert(AuthSysLog authSysLog);

    /**
     * 根据ASL_ID查询认证记录表
     * @param aslID
     * @return
     */
    AuthSysLog selectByASL_ID(@Param("aslID") String aslID);
}
