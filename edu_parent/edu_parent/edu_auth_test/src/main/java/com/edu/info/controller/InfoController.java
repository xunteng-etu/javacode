package com.edu.info.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.account.model.entity.Parent;
import com.edu.account.model.entity.Student;
import com.edu.account.model.entity.Teacher;
import com.edu.base.ResultVo;
import com.edu.info.dubbo.service.InfoService;
import org.springframework.web.bind.annotation.*;

/**
 * @program: edu_parent
 * @description:
 * @author: BaronLi
 * @create: 2019-08-05 15:43
 */
@RequestMapping("info")
@RestController
public class InfoController {

    @Reference
    InfoService infoService;

//    /**
//     * 用户信息查询（单个）
//     * @param type
//     * @param id
//     * @return
//     */
//    @RequestMapping(value = "selectInfo",method = RequestMethod.POST)
//    public ResultVo selectInfo(@RequestParam(value = "type", defaultValue = "") String type,
//                               @RequestParam(value = "id", defaultValue = "") String id) {
//        return infoService.selectInfo(type, id);
//    }
//
//    /**
//     * 用户信息查询（总数）
//     * @param type
//     * @param ids
//     * @param name
//     * @param startAge
//     * @param endAge
//     * @param sex
//     * @return
//     */
//    @RequestMapping(value = "selectInfoCounts",method = RequestMethod.POST)
//    public ResultVo selectInfoCounts(@RequestParam(value = "type", defaultValue = "") String type,
//                                     @RequestParam(value = "ids", defaultValue = "") String ids,
//                                     @RequestParam(value = "name", defaultValue = "") String name,
//                                     @RequestParam(value = "startAge",defaultValue = "") Integer startAge,
//                                     @RequestParam(value = "endAge",defaultValue = "") Integer endAge,
//                                     @RequestParam(value = "sex", defaultValue = "") String sex) {
//        return infoService.selectInfoCount(type, ids, name, startAge, endAge, sex);
//    }
//
//    /**
//     * 用户信息查询（列表）
//     * @param type
//     * @param ids
//     * @param name
//     * @param startAge
//     * @param endAge
//     * @param sex
//     * @param pageNo
//     * @param pageRow
//     * @return
//     */
//    @RequestMapping(value = "selectInfos",method = RequestMethod.POST)
//    public ResultVo selectInfos(@RequestParam(value = "type", defaultValue = "") String type,
//                                @RequestParam(value = "ids", defaultValue = "") String ids,
//                                @RequestParam(value = "name", defaultValue = "") String name,
//                                @RequestParam(value = "startAge",defaultValue = "") Integer startAge,
//                                @RequestParam(value = "endAge",defaultValue = "") Integer endAge,
//                                @RequestParam(value = "sex", defaultValue = "") String sex,
//                                @RequestParam(value = "pageNo",defaultValue = "") Integer pageNo,
//                                @RequestParam(value = "pageRow",defaultValue = "") Integer pageRow) {
//        return infoService.selectInfos(type, ids, name, startAge, endAge, sex, pageNo, pageRow);
//    }


    @RequestMapping(value = "selectTeacherByID", method = RequestMethod.POST)
    public ResultVo selectTeacherByID(@RequestParam(value = "id", defaultValue = "") String id) {
        return infoService.selectTeacherByID(id);
    }

    @RequestMapping(value = "selectParentByID", method = RequestMethod.POST)
    public ResultVo selectParentByID(@RequestParam(value = "id", defaultValue = "") String id) {
        return infoService.selectParentByID(id);
    }

    @RequestMapping(value = "selectStuByID", method = RequestMethod.POST)
    public ResultVo selectStuByID(@RequestParam(value = "id", defaultValue = "") String id) {
        return infoService.selectStuByID(id);
    }

    @RequestMapping(value = "selectTeacherCountByParam", method = RequestMethod.POST)
    public ResultVo selectTeacherCountByParam(@RequestParam(value = "ids", defaultValue = "") String ids,
                                              @RequestParam(value = "name", defaultValue = "") String name,
                                              @RequestParam(value = "startAge", defaultValue = "") Integer startAge,
                                              @RequestParam(value = "endAge", defaultValue = "") Integer endAge,
                                              @RequestParam(value = "sex", defaultValue = "") String sex) {
        return infoService.selectTeacherCountByParam(ids, name, startAge, endAge, sex);
    }

    @RequestMapping(value = "selectParentCountByParam", method = RequestMethod.POST)
    public ResultVo selectParentCountByParam(@RequestParam(value = "ids", defaultValue = "") String ids,
                                             @RequestParam(value = "name", defaultValue = "") String name,
                                             @RequestParam(value = "startAge", defaultValue = "") Integer startAge,
                                             @RequestParam(value = "endAge", defaultValue = "") Integer endAge,
                                             @RequestParam(value = "sex", defaultValue = "") String sex) {
        return infoService.selectParentCountByParam(ids, name, startAge, endAge, sex);
    }

    @RequestMapping(value = "selectStudentCountByParam", method = RequestMethod.POST)
    public ResultVo selectStudentCountByParam(@RequestParam(value = "ids", defaultValue = "") String ids,
                                              @RequestParam(value = "name", defaultValue = "") String name,
                                              @RequestParam(value = "startAge", defaultValue = "") Integer startAge,
                                              @RequestParam(value = "endAge", defaultValue = "") Integer endAge,
                                              @RequestParam(value = "sex", defaultValue = "") String sex) {
        return infoService.selectStudentCountByParam(ids, name, startAge, endAge, sex);
    }


    @RequestMapping(value = "selectTeacherByParam", method = RequestMethod.POST)
    public ResultVo selectTeacherByParam(@RequestParam(value = "ids", defaultValue = "") String ids,
                                         @RequestParam(value = "name", defaultValue = "") String name,
                                         @RequestParam(value = "startAge", defaultValue = "") Integer startAge,
                                         @RequestParam(value = "endAge", defaultValue = "") Integer endAge,
                                         @RequestParam(value = "sex", defaultValue = "") String sex,
                                         @RequestParam(value = "pageNo", defaultValue = "") Integer pageNo,
                                         @RequestParam(value = "pageRow", defaultValue = "") Integer pageRow) {
        return infoService.selectTeacherByParam(ids, name, startAge, endAge, sex, pageNo, pageRow);
    }

    @RequestMapping(value = "selectParentByParam", method = RequestMethod.POST)
    public ResultVo selectParentByParam(@RequestParam(value = "ids", defaultValue = "") String ids,
                                        @RequestParam(value = "name", defaultValue = "") String name,
                                        @RequestParam(value = "startAge", defaultValue = "") Integer startAge,
                                        @RequestParam(value = "endAge", defaultValue = "") Integer endAge,
                                        @RequestParam(value = "sex", defaultValue = "") String sex,
                                        @RequestParam(value = "pageNo", defaultValue = "") Integer pageNo,
                                        @RequestParam(value = "pageRow", defaultValue = "") Integer pageRow) {
        return infoService.selectParentByParam(ids, name, startAge, endAge, sex, pageNo, pageRow);
    }

    @RequestMapping(value = "selectStudentByParam", method = RequestMethod.POST)
    public ResultVo selectStudentByParam(@RequestParam(value = "ids", defaultValue = "") String ids,
                                         @RequestParam(value = "name", defaultValue = "") String name,
                                         @RequestParam(value = "startAge", defaultValue = "") Integer startAge,
                                         @RequestParam(value = "endAge", defaultValue = "") Integer endAge,
                                         @RequestParam(value = "sex", defaultValue = "") String sex,
                                         @RequestParam(value = "pageNo", defaultValue = "") Integer pageNo,
                                         @RequestParam(value = "pageRow", defaultValue = "") Integer pageRow) {
        return infoService.selectStudentByParam(ids, name, startAge, endAge, sex, pageNo, pageRow);
    }

    @RequestMapping(value = "updateTeacher", method = RequestMethod.POST)
    public ResultVo updateTeacher(@RequestBody Teacher teacher) {
        return infoService.updateTeacher(teacher);
    }

    @RequestMapping(value = "updateParent", method = RequestMethod.POST)
    public ResultVo updateParent(@RequestBody Parent parent) {
        return infoService.updateParent(parent);
    }

    @RequestMapping(value = "updateStudent", method = RequestMethod.POST)
    public ResultVo updateStudent(@RequestBody Student student) {
        System.out.println(student.toString());
        return infoService.updateStudent(student);
    }
}