package com.oasystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.oasystem.dao")
@EnableScheduling
public class OasystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(OasystemApplication.class, args);
    }
}
