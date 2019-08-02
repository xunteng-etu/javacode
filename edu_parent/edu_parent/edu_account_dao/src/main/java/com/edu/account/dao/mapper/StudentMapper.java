package com.edu.account.dao.mapper;

import com.edu.account.model.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StudentMapper {

    /**
     * 新增一条数据
     *
     * @param student
     */
    void insert(Student student);

    /**
     * 根据学籍号查询学生信息
     *
     * @param stuNum
     * @return
     */
    Student selectByStuNum(@Param("stuNum") String stuNum);

    /**
     * 根据学生ID查询学生信息
     *
     * @param id 学生id
     * @return
     */
    Student selectByStudentID(@Param("id") String id);
}
