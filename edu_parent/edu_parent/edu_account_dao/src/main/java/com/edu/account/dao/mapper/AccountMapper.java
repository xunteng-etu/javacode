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
     *
     * @param mobile 手机号
     * @return
     */
    Account checkRegistered(@Param("mobile") String mobile);

    /**
     * 根据用户ID查询用户密码
     *
     * @param id
     * @return
     */
    String selectPwByID(@Param("id") String id);

    /**
     * 根据用户id修改用户密码
     *
     * @param pw
     * @param id
     */
    void updatePwByID(@Param("pw") String pw, @Param("id") String id);

    /**
     * 根据ID查询账户
     *
     * @param id
     * @return
     */
    Account selectByAid(@Param("id") String id);

    /**
     * 新增一个账户
     */
    void insert(Account account);

    /**
     * 根据手机号码与密码查询
     * @param mobile 手机号码
     * @param pwd 密码
     * @return
     */
    Account getByMobileAndPwd(@Param("mobile")String mobile,@Param("pw")String pwd);
}
