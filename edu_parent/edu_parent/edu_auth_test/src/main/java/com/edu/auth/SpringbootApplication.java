package com.edu.auth;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableDubbo
public class SpringbootApplication {
    public static void main (String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
