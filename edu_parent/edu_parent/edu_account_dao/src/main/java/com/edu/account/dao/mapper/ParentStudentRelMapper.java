package com.edu.account.dao.mapper;

import com.edu.account.model.entity.ParentStudentRel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ParentStudentRelMapper {

    void insert(ParentStudentRel parentStudentRel);
}
