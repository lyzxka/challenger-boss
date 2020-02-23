package com.rancii;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.rancii.dao")
@SpringBootApplication
public class ChallengerBossApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChallengerBossApplication.class, args);
    }

}
