package com.edu.account.dao.mapper;

import com.edu.account.model.entity.Parent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ParentMapper {
    /**
     * 新增一条数据
     *
     * @param parent
     */
    void insert(Parent parent);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    Parent selectByID(@Param("id") String id);

    /**
     * 多参数组合查询
     *
     * @param ids      id
     * @param name     名称
     * @param startAge 年龄段（大于等于）
     * @param endAge   年龄段（小于等于）
     * @param sex      性别
     * @param pageNo   页码
     * @param pageRow  行数
     * @return
     */
    List<Parent> selectByParams(@Param("ids") String ids, @Param("name") String name,
                                @Param("startAge") Integer startAge, @Param("endAge") Integer endAge,
                                @Param("sex") String sex,
                                @Param("pageNo") Integer pageNo, @Param("pageRow") Integer pageRow);

    /**
     * 多参数组合查询总数
     *
     * @param ids      id
     * @param name     名称
     * @param startAge 年龄段（大于等于）
     * @param endAge   年龄段（小于等于）
     * @param sex      性别
     * @return
     */
    Integer selectCountByParams(@Param("ids") String ids, @Param("name") String name,
                                @Param("startAge") Integer startAge, @Param("endAge") Integer endAge,
                                @Param("sex") String sex);

    /**
     * 修改
     *
     * @param parent
     */
    void update(Parent parent);
}
