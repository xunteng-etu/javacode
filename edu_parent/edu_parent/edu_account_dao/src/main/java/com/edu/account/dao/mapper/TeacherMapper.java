package com.edu.account.dao.mapper;

import com.edu.account.model.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TeacherMapper {
    void insert(Teacher teacher);
}
