package com.edu.base;

import com.edu.base.Constant;

/**
 * @program: edu_parent
 * @description: 统一返回实体类
 * @author: BaronLi
 * @create: 2019-06-24 13:55
 */
public class ResultVo<T> {
    private String rt_code = Constant.RESULT_CODE_WONGSYSTEM;
    private String rt_msg = Constant.RESULT_MSG_WONGSYSTEM;
    private T rt_data;

    public String getRt_code() {
        return rt_code;
    }

    public void setRt_code(String rt_code) {
        this.rt_code = rt_code;
    }

    public String getRt_msg() {
        return rt_msg;
    }

    public void setRt_msg(String rt_msg) {
        this.rt_msg = rt_msg;
    }

    public T getRt_data() {
        return rt_data;
    }

    public void setRt_data(T rt_data) {
        this.rt_data = rt_data;
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "rt_code='" + rt_code + '\'' +
                ", rt_msg='" + rt_msg + '\'' +
                ", rt_data=" + rt_data +
                '}';
    }
}
