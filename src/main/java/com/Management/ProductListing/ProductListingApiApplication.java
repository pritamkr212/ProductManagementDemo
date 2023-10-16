package com.Management.ProductListing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
@EnableAsync
public class ProductListingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductListingApiApplication.class, args);
	}
    @Bean("AsyncExecutor")
	public TaskExecutor getAsyncExecutor(){
		ThreadPoolTaskExecutor threadPoolTaskExecutor=new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(5);
		threadPoolTaskExecutor.setMaxPoolSize(8);
		threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		threadPoolTaskExecutor.setThreadNamePrefix("Async-processing");
		return threadPoolTaskExecutor;
	}
}
