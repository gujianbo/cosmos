package com.jianbogu.cosmos.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig {
    @Bean("element_operator")
    public AsyncTaskExecutor asyncTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(300);
        threadPoolTaskExecutor.setCorePoolSize(200);
        threadPoolTaskExecutor.setThreadNamePrefix("element_operator");
        threadPoolTaskExecutor.initialize();

        return threadPoolTaskExecutor;
    }
}
