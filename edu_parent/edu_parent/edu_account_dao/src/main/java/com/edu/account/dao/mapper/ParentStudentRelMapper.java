package com.edu.account.dao.mapper;

import com.edu.account.model.entity.ParentStudentRel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ParentStudentRelMapper {

    /**
     * 新增一条数据
     * @param parentStudentRel
     */
    void insert(ParentStudentRel parentStudentRel);

    ParentStudentRel selectByID(@Param("relID") String relID);

    ParentStudentRel selectByParentIDAndStuID(@Param("parentID")String parentID,@Param("studentID")String studentID);
}
