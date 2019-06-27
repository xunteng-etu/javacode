package com.edu.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.omg.CORBA.INTERNAL;

import java.util.UUID;

/**
 * @program: edu_parent
 * @description: 随机密钥生成工具类
 * @author: BaronLi
 * @create: 2019-06-27 15:22
 */
public class RandomUtils {
    /**
     * 获取接入系统标识
     *
     * @return 系统标识
     */
    public static String GET_ASID() {
        return RandomStringUtils.randomAlphanumeric(12);
    }

    /**
     * 获取系统授权标识
     *
     * @return 系统授权标识
     */
    public static String GET_CLIENT_ID() {
        return RandomStringUtils.randomAlphanumeric(12);
    }

    /**
     * 获取系统授权秘钥
     *
     * @return 系统授权秘钥
     */
    public static String GET_CLIENT_SECRET() {
        return RandomStringUtils.randomAlphanumeric(32);
    }

    /**
     * 获取系统加密秘钥
     *
     * @return 系统加密秘钥
     */
    public static String GET_CLIENT_KEY() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    /**
     * 根据指定位数获取相对于位数的随机密钥
     *
     * @param number 生成密钥位数
     * @return
     */
    public static String GET_RANDOMSTRING(Integer number) {
        return RandomStringUtils.randomAlphanumeric(number);
    }
}
