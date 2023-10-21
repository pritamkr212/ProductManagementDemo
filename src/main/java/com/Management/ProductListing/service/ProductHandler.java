package com.Management.ProductListing.service;

import com.Management.ProductListing.interceptor.GeneralInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
@Component
public class ProductHandler {
    private Logger logger= LoggerFactory.getLogger(GeneralInterceptor.class);
    @Async("asyncExecution")
    public void handlerRequest(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
        logger.info("postHandle is invoked...{}: {}",request.getRequestURI(),request.getMethod());
        if(response.getStatus()==201){
            logger.info("Processed SuccessFully ID: {}",response.getHeader("X-Response-ID"));
        }
        else{
            logger.debug("Processed Failed ID: {}",response.getHeader("X-Response-ID"));
        }
    }
}
