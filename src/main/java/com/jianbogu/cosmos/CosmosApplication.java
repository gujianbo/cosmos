package com.jianbogu.cosmos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//开启异步调用
@EnableAsync
public class CosmosApplication {
    public static void main(String[] args) {
        SpringApplication.run(CosmosApplication.class, args);
    }
}
