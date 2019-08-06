package com.edu.account.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.account.dao.mapper.*;
import com.edu.account.model.entity.*;
import com.edu.base.Constant;
import com.edu.base.ResultVo;
import com.edu.sms.dubbo.service.SendSmsService;
import com.edu.utils.RandomUtils;
import com.edu.utils.RedisUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @program: edu_parent
 * @description: 账号业务逻辑层
 * @author: BaronLi
 * @create: 2019-07-25 14:02
 */
@Service
public class AccountService {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private AccountMapper accountMapper;
    @Reference
    private SendSmsService sendSmsService;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private ParentMapper parentMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ParentStudentRelMapper parentStudentRelMapper;

    /**
     * 查询手机号是否已注册
     *
     * @param mobile 手机号
     * @return
     */
    public ResultVo checkRegistered(String mobile) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(mobile)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        try {
            Account account = accountMapper.checkRegistered(mobile);
            if (account != null) {
                resultVo.setRt_code(Constant.RESULT_CODE_MOBILEISEXISTS);
                resultVo.setRt_msg(Constant.RESULT_MSG_MOBILEISEXISTS);
                resultVo.setRt_data(account);
            }
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVo;
    }

    /**
     * 创建密码服务
     *
     * @param id  账号ID
     * @param pwd 密码
     * @return
     */
    public ResultVo createPwd(String id, String pwd) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(id) || "".equals(pwd)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        String result = accountMapper.selectPwByID(id);
        if (result != null) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGSYSTEM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGSYSTEM);
            return resultVo;
        }
        try {
            //密码采用md5加密后截取前12位
            String password = DigestUtils.md5Hex(pwd).substring(0, 12);
            accountMapper.updatePwByID(password, id);
            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVo;
    }


    /**
     * 手机注册验证码
     *
     * @param mobile       手机号码
     * @param asid         接入系统标识
     * @param templatecode 短信模板编号
     * @param signName     短信签名
     * @return
     */
    public ResultVo sendRegister(String mobile, String asid, String templatecode, String signName) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(mobile)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        String resule = checkRegistered(mobile).getRt_code();
        if (!Constant.RESULT_CODE_SUCCES.equals(resule)) {
            resultVo.setRt_code(Constant.RESULT_CODE_MOBILEISEXISTS);
            resultVo.setRt_msg(Constant.RESULT_MSG_MOBILEISEXISTS);
        }
        //生成随机6位数
        String code = RandomUtils.GET_RANDOMNUMBER(6);
        //短信模板
        String templateParam = "{\"code\":\"" + code + "\"}";
        //保存进redis,有效期为5分钟
        redisUtils.setObj(mobile + Constant.REDIS_KEY_REGISTER_CODE, code, 5, TimeUnit.MINUTES);
        try {
            resultVo = sendSmsService.sendSms(asid, templatecode, mobile, signName, templateParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVo;
    }

    /**
     * 用户注册
     *
     * @param mobile 注册手机
     * @param code   验证码
     * @param roler  角色（1.教师 2.家长 3.学生）
     * @return
     */
    public ResultVo register(String mobile, String code, String roler) {
        ResultVo resultVo = new ResultVo();
        if ("".equals(mobile) || "".equals(code) || "".equals(roler)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        ResultVo result = checkRegistered(mobile);
        if (result.getRt_data() != null) {
            resultVo.setRt_code(Constant.RESULT_CODE_MOBILEISEXISTS);
            resultVo.setRt_msg(Constant.RESULT_MSG_MOBILEISEXISTS);
            resultVo.setRt_data(mobile);
            return resultVo;
        }
        if (!code.equals(redisUtils.getObj(mobile + Constant.REDIS_KEY_REGISTER_CODE))) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGCODE);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGCODE);
            resultVo.setRt_data(code);
            return resultVo;
        }
        try {
            Account account = new Account();
            String id = RandomUtils.GET_RANDOMSTRING(12);
            account.setId(id);
            account.setMobile(mobile);
            account.setCreateTime(new Date());
            account.setSerial(0);
            account.setSysStatus("1");
            account.setValueFlag("1");
            accountMapper.insert(account);
            if (roler.equals("1")) {
                //教师
                Teacher teacher = new Teacher();
                teacher.setAid(id);
                teacher.setCreateTime(new Date());
                teacher.setSerial(0);
                teacher.setSysStatus("1");
                teacher.setValueFlag("1");
                teacherMapper.insert(teacher);
            } else if (roler.equals("2")) {
                //家长
                Parent parent = new Parent();
                parent.setAid(id);
                parent.setCreateTime(new Date());
                parent.setSerial(0);
                parent.setSysStatus("1");
                parent.setValueFlag("1");
                parentMapper.insert(parent);
            } else if (roler.equals("3")) {
                //学生
                Student student = new Student();
                student.setAid(id);
                student.setCreateTime(new Date());
                student.setSerial(0);
                student.setSysStatus("1");
                student.setValueFlag("1");
                studentMapper.insert(student);
            } else {
                resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
                resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
                return resultVo;
            }

            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVo;
    }

    /**
     * 家长注册学生服务
     *
     * @param stuNum 学籍号
     * @param name   学生姓名
     * @param sex    性别
     * @param date   出生日期
     * @param rel    家长与该学生关系：（1：母，2：父，3：祖母，4：祖父，5：外婆：6：外公，9：其他亲戚）
     * @param token  家长登录返回token，用于判断登录信息是否失效
     * @return
     */
    public ResultVo parentRegister(String stuNum, String name, String sex, String date, String rel, String token) {
        //关系数组
        String[] rels = {"1", "2", "3", "4", "5", "6", "9"};
        ResultVo resultVo = new ResultVo();
        if ("".equals(stuNum) || "".equals(name) || "".equals(sex) || "".equals(date) || "".equals(rel) || "".equals(token)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        //校验关系参数是否有效
        if (!ArrayUtils.contains(rels, rel)) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGPARAM);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGPARAM);
            return resultVo;
        }
        //判断家长登录信息是否有效
        Account parent = (Account) redisUtils.getObj(token + Constant.REDIS_KEY_LOGIN);
        if (parent == null) {
            resultVo.setRt_code(Constant.RESULT_CODE_WONGTOKEN);
            resultVo.setRt_msg(Constant.RESULT_MSG_WONGTOKEN);
            return resultVo;
        }
        //判断该学籍号是否已被注册
        Student student = studentMapper.selectByStuNum(stuNum);
        //提取家长与学生关系实体
        String relID = RandomUtils.GET_RANDOMSTRING(12);
        ParentStudentRel parentStudentRel = new ParentStudentRel();
        parentStudentRel.setRelID(relID);
        parentStudentRel.setParentID(parent.getId());
        parentStudentRel.setRef(rel);
        parentStudentRel.setSerial(0);
        parentStudentRel.setSysStatus("1");
        parentStudentRel.setCreateTime(new Date());
        parentStudentRel.setValueFlag("1");
        //绑定到家长与学生关系表
        if (student != null) {
            ParentStudentRel ps = parentStudentRelMapper.selectByParentIDAndStuID(parent.getId(),student.getAid());
            //已经绑定过
            if(ps != null){
                resultVo.setRt_code(Constant.RESULT_CODE_WONGSTUBING);
                resultVo.setRt_msg(Constant.RESULT_MSG_WONGSTUBING);
                resultVo.setRt_data(student);
                return resultVo;
            }
            parentStudentRel.setStudentID(student.getAid());
            parentStudentRelMapper.insert(parentStudentRel);
            resultVo.setRt_code(Constant.RESULT_CODE_STUISREGISTER);
            resultVo.setRt_msg(Constant.RESULT_MSG_STUISREGISTER);
            resultVo.setRt_data(student);
        } else {
            //新增账户信息
            Account account = new Account();
            String aid = RandomUtils.GET_RANDOMSTRING(12);
            account.setId(aid);
            account.setCreateTime(new Date());
            account.setSerial(0);
            account.setSysStatus("1");
            account.setValueFlag("1");
            accountMapper.insert(account);
            //新增学生信息
            Student stu = new Student();
            stu.setAid(aid);
            stu.setName(name);
            stu.setSex(sex);
            stu.setBarfdate(date);
            stu.setStuNum(stuNum);
            stu.setSerial(0);
            stu.setSysStatus("1");
            stu.setValueFlag("1");
            stu.setCreateTime(new Date());
            studentMapper.insert(stu);
            //绑定家长与学生关系
            parentStudentRel.setStudentID(stu.getAid());
            parentStudentRelMapper.insert(parentStudentRel);

            resultVo.setRt_code(Constant.RESULT_CODE_SUCCES);
            resultVo.setRt_msg(Constant.RESULT_MSG_SUCCES);
            resultVo.setRt_data(stu);
        }
        return resultVo;
    }
}
