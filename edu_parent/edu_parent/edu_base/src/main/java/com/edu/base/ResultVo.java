package com.edu.base;

import com.edu.base.Constant;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: edu_parent
 * @description: 统一返回实体类
 * @author: BaronLi
 * @create: 2019-06-24 13:55
 */
@Data
public class ResultVo<T> implements Serializable {
    private String rt_code = Constant.RESULT_CODE_WONGSYSTEM;
    private String rt_msg = Constant.RESULT_MSG_WONGSYSTEM;
    private T rt_data;

}
