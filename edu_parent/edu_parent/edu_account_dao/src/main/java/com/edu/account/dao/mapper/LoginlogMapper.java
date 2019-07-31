package com.edu.account.dao.mapper;

import com.edu.account.model.entity.Loginlog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginlogMapper {

    /**
     * 新增一条数据
     * @param loginlog
     */
    void insert(Loginlog loginlog);

    /**
     * 根据标识查询
     * @param id
     * @return
     */
    Loginlog getByID(@Param("loginlogID")String id);

}
