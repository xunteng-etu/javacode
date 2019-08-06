package com.edu.info.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.edu.account.model.entity.Parent;
import com.edu.account.model.entity.Student;
import com.edu.account.model.entity.Teacher;
import com.edu.base.ResultVo;
import com.edu.info.dubbo.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: edu_parent
 * @description: 用户信息实现类
 * @author: BaronLi
 * @create: 2019-08-05 15:22
 */
@Service(timeout = 5000, retries = -1)
public class InfoServiceImpl implements InfoService {

    @Autowired
    private com.edu.account.service.InfoService infoService;


    public ResultVo selectTeacherByID(String id) {
        return infoService.selectTeacherByID(id);
    }

    public ResultVo selectParentByID(String id) {
        return infoService.selectParentByID(id);
    }

    public ResultVo selectStuByID(String id) {
        return infoService.selectStuByID(id);
    }

    public ResultVo selectTeacherByParam(String ids, String name, Integer startAge, Integer endAge, String sex, Integer pageNo, Integer pageRow) {
        return infoService.selectTeacherByParam(ids, name, startAge, endAge, sex, pageNo, pageRow);
    }

    public ResultVo selectParentByParam(String ids, String name, Integer startAge, Integer endAge, String sex, Integer pageNo, Integer pageRow) {
        return infoService.selectParentByParam(ids, name, startAge, endAge, sex, pageNo, pageRow);
    }

    public ResultVo selectStudentByParam(String ids, String name, Integer startAge, Integer endAge, String sex, Integer pageNo, Integer pageRow) {
        return infoService.selectStudentByParam(ids, name, startAge, endAge, sex, pageNo, pageRow);
    }

    public ResultVo selectTeacherCountByParam(String ids, String name, Integer startAge, Integer endAge, String sex) {
        return infoService.selectTeacherCountByParam(ids, name, startAge, endAge, sex);
    }

    public ResultVo selectParentCountByParam(String ids, String name, Integer startAge, Integer endAge, String sex) {
        return infoService.selectParentCountByParam(ids, name, startAge, endAge, sex);
    }

    public ResultVo selectStudentCountByParam(String ids, String name, Integer startAge, Integer endAge, String sex) {
        return infoService.selectStudentCountByParam(ids, name, startAge, endAge, sex);
    }

    public ResultVo updateTeacher(Teacher teacher) {
        return infoService.updateTeacher(teacher);
    }

    public ResultVo updateParent(Parent parent) {
        return infoService.updateParent(parent);
    }

    public ResultVo updateStudent(Student student) {
        return infoService.updateStudent(student);
    }
}
