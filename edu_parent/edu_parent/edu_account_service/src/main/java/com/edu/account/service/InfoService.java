package com.edu.account.service;

import com.edu.account.dao.mapper.ParentMapper;
import com.edu.account.dao.mapper.StudentMapper;
import com.edu.account.dao.mapper.TeacherMapper;
import com.edu.account.model.entity.Parent;
import com.edu.account.model.entity.Student;
import com.edu.account.model.entity.Teacher;
import com.edu.base.Constant;
import com.edu.base.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: edu_parent
 * @description: 用户信息中心逻辑层
 * @author: BaronLi
 * @create: 2019-08-05 10:14
 */
@Service
public class InfoService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private ParentMapper parentMapper;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 用户信息查询（单个）
     *
     * @param type 角色类型（1.教师 2.家长 3.学生）
     * @param id   ID
     * @return
     */
//    public ResultVo selectInfo(String type, String id) {
//        ResultVo resultVo = new ResultVo();
//        if ("".equals(type) || "".equals(id)) {
//            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
//            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
//            return resultVo;
//        }
//        resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
//        resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
//        if ("1".equals(type)) {
//            resultVo.setRt_data(selectTeacherByID(id));
//        } else if ("2".equals(type)) {
//            resultVo.setRt_data(selectParentByID(id));
//        } else if ("3".equals(type)) {
//            resultVo.setRt_data(selectStuByID(id));
//        } else {
//            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
//            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
//        }
//        return resultVo;
//    }

    /**
     * 根据ID查询教师
     *
     * @param id
     * @return
     */
    public ResultVo selectTeacherByID(String id) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(id)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
        resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
        resultVo.setRt_data(teacherMapper.selectByID(id));
        return resultVo;
    }

    /**
     * 根据ID查询家长
     *
     * @param id
     * @return
     */
    public ResultVo selectParentByID(String id) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(id)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
        resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
        resultVo.setRt_data(parentMapper.selectByID(id));
        return resultVo;
    }

    /**
     * 根据ID查询学生
     *
     * @param id
     * @return
     */
    public ResultVo selectStuByID(String id) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(id)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
        resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
        resultVo.setRt_data(studentMapper.selectByStudentID(id));
        return resultVo;
    }

    /**
     * 用户信息查询（列表）
     *
     * @param type     角色类型
     * @param ids      ids
     * @param name     名称
     * @param startAge 年龄段（大于等于）
     * @param endAge   年龄段（小于等于）
     * @param sex      性别
     * @param pageNo   页码 默认第一页
     * @param pageRow  行数 默认50条
     * @return
     */
//    public ResultVo selectInfos(String type, String ids, String name, Integer startAge,
//                                Integer endAge, String sex, Integer pageNo, Integer pageRow) {
//        ResultVo resultVo = new ResultVo();
//        if ("".equals(type) || "".equals(ids)) {
//            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
//            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
//            return resultVo;
//        }
//        if (startAge != null && endAge != null && endAge < startAge) {
//            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
//            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
//            return resultVo;
//        }
//        if (pageNo == null) {
//            pageNo = 0;
//        }
//        if (pageRow == null || pageRow <= 0) {
//            pageRow = 50;
//        }
//        //如果前端传过来的ids最后有个“，”，则删除掉
//        if (ids.endsWith(",")) {
//            ids = ids.substring(0, ids.length() - 1);
//        }
//        resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
//        resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
//        if ("1".equals(type)) {
//            resultVo.setRt_data(selectTeacherByParam(ids, name, startAge, endAge, sex, pageNo, pageRow));
//        } else if ("2".equals(type)) {
//            resultVo.setRt_data(selectParentByParam(ids, name, startAge, endAge, sex, pageNo, pageRow));
//        } else if ("3".equals(type)) {
//            resultVo.setRt_data(selectStudentByParam(ids, name, startAge, endAge, sex, pageNo, pageRow));
//        } else {
//            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
//            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
//        }
//        return resultVo;
//    }

    /**
     * 多参数组合查询教师
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
    public ResultVo selectTeacherByParam(String ids, String name, Integer startAge,
                                         Integer endAge, String sex, Integer pageNo, Integer pageRow) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(ids)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        if (startAge != null && endAge != null && endAge < startAge) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        if (pageNo == null) {
            pageNo = 0;
        }
        if (pageRow == null || pageRow <= 0) {
            pageRow = 50;
        }
        //如果前端传过来的ids最后有个“，”，则删除掉
        if (ids.endsWith(",")) {
            ids = ids.substring(0, ids.length() - 1);
        }
        resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
        resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
        resultVo.setRt_data(teacherMapper.selectByParams(ids, name, startAge, endAge, sex, pageNo, pageRow));
        return resultVo;
    }

    /**
     * 多参数组合查询家长
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
    public ResultVo selectParentByParam(String ids, String name, Integer startAge,
                                        Integer endAge, String sex, Integer pageNo, Integer pageRow) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(ids)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        if (startAge != null && endAge != null && endAge < startAge) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        if (pageNo == null) {
            pageNo = 0;
        }
        if (pageRow == null || pageRow <= 0) {
            pageRow = 50;
        }
        //如果前端传过来的ids最后有个“，”，则删除掉
        if (ids.endsWith(",")) {
            ids = ids.substring(0, ids.length() - 1);
        }
        resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
        resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
        resultVo.setRt_data(parentMapper.selectByParams(ids, name, startAge, endAge, sex, pageNo, pageRow));
        return resultVo;
    }

    /**
     * 多参数组合查询学生
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
    public ResultVo selectStudentByParam(String ids, String name, Integer startAge,
                                         Integer endAge, String sex, Integer pageNo, Integer pageRow) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(ids)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        if (startAge != null && endAge != null && endAge < startAge) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        if (pageNo == null) {
            pageNo = 0;
        }
        if (pageRow == null || pageRow <= 0) {
            pageRow = 50;
        }
        //如果前端传过来的ids最后有个“，”，则删除掉
        if (ids.endsWith(",")) {
            ids = ids.substring(0, ids.length() - 1);
        }
        resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
        resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
        resultVo.setRt_data(studentMapper.selectByParams(ids, name, startAge, endAge, sex, pageNo, pageRow));
        return resultVo;
    }

    /**
     * 用户信息查询（总数）
     *
     * @param type     角色类型
     * @param ids      ids
     * @param name     名称
     * @param startAge 年龄段（大于等于）
     * @param endAge   年龄段（小于等于）
     * @param sex      性别
     * @return
     */
//    public ResultVo selectInfoCount(String type, String ids, String name, Integer startAge, Integer endAge, String sex) {
//        ResultVo resultVo = new ResultVo();
//        if ("".equals(type) || "".equals(ids)) {
//            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
//            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
//            return resultVo;
//        }
//        if (startAge != null && endAge != null && endAge < startAge) {
//            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
//            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
//            return resultVo;
//        }
//        resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
//        resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
//        if ("1".equals(type)) {
//            resultVo.setRt_data(selectTeacherCountByParam(ids, name, startAge, endAge, sex));
//        } else if ("2".equals(type)) {
//            resultVo.setRt_data(selectParentCountByParam(ids, name, startAge, endAge, sex));
//        } else if ("3".equals(type)) {
//            resultVo.setRt_data(selectStudentCountByParam(ids, name, startAge, endAge, sex));
//        } else {
//            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
//            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
//        }
//        return resultVo;
//    }

    /**
     * 多参数组合查询教师总数
     *
     * @param ids      id
     * @param name     名称
     * @param startAge 年龄段（大于等于）
     * @param endAge   年龄段（小于等于）
     * @param sex      性别
     * @return
     */
    public ResultVo selectTeacherCountByParam(String ids, String name, Integer startAge, Integer endAge, String sex) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(ids)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        if (startAge != null && endAge != null && endAge < startAge) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
        resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
        resultVo.setRt_data(teacherMapper.selectCountByParams(ids, name, startAge, endAge, sex));
        return resultVo;
    }

    /**
     * 多参数组合查询家长总数
     *
     * @param ids      id
     * @param name     名称
     * @param startAge 年龄段（大于等于）
     * @param endAge   年龄段（小于等于）
     * @param sex      性别
     * @return
     */
    public ResultVo selectParentCountByParam(String ids, String name, Integer startAge, Integer endAge, String sex) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(ids)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        if (startAge != null && endAge != null && endAge < startAge) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
        resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
        resultVo.setRt_data(parentMapper.selectCountByParams(ids, name, startAge, endAge, sex));
        return resultVo;
    }

    /**
     * 多参数组合查询学生总数
     *
     * @param ids      id
     * @param name     名称
     * @param startAge 年龄段（大于等于）
     * @param endAge   年龄段（小于等于）
     * @param sex      性别
     * @return
     */
    public ResultVo selectStudentCountByParam(String ids, String name, Integer startAge, Integer endAge, String sex) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(ids)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        if (startAge != null && endAge != null && endAge < startAge) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
        resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
        resultVo.setRt_data(studentMapper.selectCountByParams(ids, name, startAge, endAge, sex));
        return resultVo;
    }

    /**
     * 修改教师信息
     *
     * @param teacher
     * @return
     */
    public ResultVo updateTeacher(Teacher teacher) {
        ResultVo resultVo = new ResultVo();
        if (teacher.getAid() == null || "".equals(teacher.getAid())) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        teacher.setUpdateTime(new Date());
        teacherMapper.update(teacher);
        resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
        resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
        resultVo.setRt_data(teacher);
        return resultVo;
    }

    /**
     * 修改家长信息
     *
     * @param parent
     * @return
     */
    public ResultVo updateParent(Parent parent) {
        ResultVo resultVo = new ResultVo();
        if (parent.getAid() == null || "".equals(parent.getAid())) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        parent.setUpdateTime(new Date());
        parentMapper.update(parent);
        resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
        resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
        resultVo.setRt_data(parent);
        return resultVo;
    }

    /**
     * 修改学生信息
     *
     * @param student
     * @return
     */
    public ResultVo updateStudent(Student student) {
        ResultVo resultVo = new ResultVo();
        if (student.getAid() == null || "".equals(student.getAid())) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        student.setUpdateTime(new Date());
        studentMapper.update(student);
        resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
        resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
        resultVo.setRt_data(student);
        return resultVo;
    }
}
