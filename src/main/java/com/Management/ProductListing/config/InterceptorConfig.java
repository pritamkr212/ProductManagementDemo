package com.Management.ProductListing.config;

import com.Management.ProductListing.interceptor.GeneralInterceptor;
import com.Management.ProductListing.service.EventLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private EventLogService eventLogService;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GeneralInterceptor(eventLogService));
    }
}

