package com.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: edu_parent
 * @description:
 * @author: BaronLi
 * @create: 2019-06-25 13:39
 */
@MapperScan("com.edu.auth.model.mapper")
@SpringBootApplication
public class SpringbootApplication {
    public static void main (String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
