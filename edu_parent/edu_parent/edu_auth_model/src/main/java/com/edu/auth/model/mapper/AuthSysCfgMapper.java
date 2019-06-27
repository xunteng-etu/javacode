package com.edu.auth.model.mapper;

import com.edu.auth.model.entity.AuthSysCfg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AuthSysCfgMapper {
    /**
     * 新增
     *
     * @param authSysCfg
     * @return
     */
    void insert(AuthSysCfg authSysCfg);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    void delete(String id);

    /**
     * 接入系统配置修改接口
     *
     * @param clientName 接入系统名称
     * @param clientIP   授权IP
     * @param remark     备注
     * @param asID       接入系统标识
     * @param updateTime 修改时间
     */
    void update(@Param("clientName") String clientName,
                @Param("clientIP") String clientIP,
                @Param("remark") String remark,
                @Param("updateTime") Date updateTime,
                @Param("asID") String asID);

    /**
     * 根据id查询单个
     *
     * @param id
     * @return
     */
    AuthSysCfg selectByASID(String id);

    /**
     * 根据clientID查询单个
     *
     * @param clientID
     * @return
     */
    AuthSysCfg selectByClientID(@Param("clientID") String clientID);

    /**
     * 根据clientSecret查询单个
     *
     * @param clientSecret
     * @return
     */
    AuthSysCfg selectByClientSecret(@Param("clientSecret") String clientSecret);

    /**
     * 根据clientKey查询单个
     *
     * @param clientKey
     * @return
     */
    AuthSysCfg selectByClientKey(@Param("clientKey") String clientKey);

    /**
     * 接入系统配置查询列表接口
     *
     * @param asID
     * @param clientName
     * @param pageNo
     * @param pageRow
     * @return
     */
    List<AuthSysCfg> findAll(@Param("asID") String asID, @Param("clientName") String clientName,
                             @Param("pageNo") Integer pageNo, @Param("pageRow") Integer pageRow);

    /**
     * 根据系统授权标识与系统授权秘钥查询接入系统配置
     * @param clientId 统授权标识
     * @param clientSecret 统授权秘钥
     * @return
     */
    AuthSysCfg selectByClientIdAndClientSecret(@Param("clientId")String clientId,@Param("clientSecret")String clientSecret);

}
