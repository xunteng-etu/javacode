package com.edu;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: edu_parent
 * @description:
 * @author: BaronLi
 * @create: 2019-06-25 13:39
 */
@SpringBootApplication
@EnableDubbo
public class SpringbootApplication {
    public static void main (String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
