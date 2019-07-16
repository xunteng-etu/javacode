package com.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program: edu_parent
 * @description:
 * @author: BaronLi
 * @create: 2019-06-25 13:39
 */
@EnableTransactionManagement
@EnableScheduling
@SpringBootApplication
public class SpringbootApplication {
    public static void main (String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
