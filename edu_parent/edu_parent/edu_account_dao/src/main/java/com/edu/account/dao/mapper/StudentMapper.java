package com.edu.account.dao.mapper;

import com.edu.account.model.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StudentMapper {
    void insert(Student student);
}
