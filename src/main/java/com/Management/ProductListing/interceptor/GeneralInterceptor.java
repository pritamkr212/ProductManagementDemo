package com.Management.ProductListing.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class GeneralInterceptor implements HandlerInterceptor {

    private Logger logger= LoggerFactory.getLogger(GeneralInterceptor.class);
    @Override
    @Async("asyncExecution")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("preHandle is invoked...{}: {}",request.getRequestURI(),request.getMethod());
        request.getRequestedSessionId();
        if(request.getRequestURI().equals("/api/v1/saveProduct")){
            logger.info("Product Data is Fetching",request.getRequestURI(),request.getMethod());
        }
        return true;
    }

    @Override
    @Async("asyncExecution")
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle is invoked...{}: {}",request.getRequestURI(),request.getMethod());

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
