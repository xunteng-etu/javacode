package com.edu.info.dubbo.service;

import com.edu.base.ResultVo;

public interface InfoService {

//    /**
//     * 用户信息查询（单个）
//     *
//     * @param type 角色类型（1.教师 2.家长 3.学生）
//     * @param id   ID
//     * @return
//     */
//    ResultVo selectInfo(String type, String id);
//
//    /**
//     * 用户信息查询（列表）
//     *
//     * @param type     角色类型
//     * @param ids      ids
//     * @param name     名称
//     * @param startAge 年龄段（大于等于）
//     * @param endAge   年龄段（小于等于）
//     * @param sex      性别
//     * @param pageNo   页码 默认第一页
//     * @param pageRow  行数 默认50条
//     * @return
//     */
//    ResultVo selectInfos(String type, String ids, String name, Integer startAge,
//                         Integer endAge, String sex, Integer pageNo, Integer pageRow);
//
//    /**
//     * 用户信息查询（总数）
//     *
//     * @param type     角色类型
//     * @param ids      ids
//     * @param name     名称
//     * @param startAge 年龄段（大于等于）
//     * @param endAge   年龄段（小于等于）
//     * @param sex      性别
//     * @return
//     */
//    ResultVo selectInfoCount(String type, String ids, String name, Integer startAge, Integer endAge, String sex);

    /**
     * 根据ID查询教师
     *
     * @param id
     * @return
     */
    ResultVo selectTeacherByID(String id);

    /**
     * 根据ID查询家长
     *
     * @param id
     * @return
     */
    ResultVo selectParentByID(String id);

    /**
     * 根据ID查询学生
     *
     * @param id
     * @return
     */
    ResultVo selectStuByID(String id);

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
    ResultVo selectTeacherByParam(String ids, String name, Integer startAge,
                                  Integer endAge, String sex, Integer pageNo, Integer pageRow);

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
    ResultVo selectParentByParam(String ids, String name, Integer startAge,
                                 Integer endAge, String sex, Integer pageNo, Integer pageRow);

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
    ResultVo selectStudentByParam(String ids, String name, Integer startAge,
                                  Integer endAge, String sex, Integer pageNo, Integer pageRow);

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
    ResultVo selectTeacherCountByParam(String ids, String name, Integer startAge, Integer endAge, String sex);

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
    ResultVo selectParentCountByParam(String ids, String name, Integer startAge, Integer endAge, String sex);

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
    ResultVo selectStudentCountByParam(String ids, String name, Integer startAge, Integer endAge, String sex);
}
