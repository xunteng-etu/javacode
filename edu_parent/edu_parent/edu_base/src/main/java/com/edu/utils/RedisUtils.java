package com.edu.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisUtils {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> valOpsObj;

    /**
     * 根据key获取Str缓存
     *
     * @param key
     * @return
     */
    public String getStr(String key) {
        return valOpsStr.get(key);
    }

    /**
     * 设置Str缓存
     *
     * @param key
     * @param val
     * @param timeout 有效时间
     * @param unit 时间单位
     */
    public void setStr(String key, String val,long timeout,TimeUnit unit) {
        valOpsStr.set(key, val,timeout,unit);
    }

    /**
     * 根据key删除Str缓存
     *
     * @param key
     */
    public void delStr(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 设置object缓存
     *
     * @param o
     * @return
     */
    public void setObj(Object k, Object o,long timeout,TimeUnit unit) {
        valOpsObj.set(k, o,timeout,unit);
    }

    /**
     * 根据key获取object缓存
     *
     * @param o
     * @return
     */
    public Object getObj(Object o) {
        return valOpsObj.get(o);
    }

    /**
     * 删除object缓存
     *
     * @param o
     */
    public void delObj(Object o) {
        redisTemplate.delete(o);
    }
}
