package com.edu.account.dao.mapper;

import com.edu.account.model.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountMapper {

    /**
     * 查询手机号是否已注册
     * @param mobile 手机号
     * @return
     */
    Account checkRegistered(@Param("mobile")String mobile);

    /**
     * 根据用户ID查询用户密码
     * @param id
     * @return
     */
    String selectPwByID(@Param("id")String id);

    /**
     * 根据用户id修改用户密码
     * @param pw
     * @param id
     */
    void updatePwByID(@Param("pw")String pw,@Param("id")String id);
}
