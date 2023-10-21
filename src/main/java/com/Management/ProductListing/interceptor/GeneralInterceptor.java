package com.Management.ProductListing.interceptor;

import com.Management.ProductListing.model.EventLog;
import com.Management.ProductListing.service.EventLogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Component
public class GeneralInterceptor implements HandlerInterceptor {
    private EventLogService eventLogService;
    public GeneralInterceptor(EventLogService eventLogService){
        this.eventLogService=eventLogService;
    }
    private Logger logger= LoggerFactory.getLogger(GeneralInterceptor.class);
    @Override
    @Async("AsyncExecutor")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("preHandle is invoked...{}: {}",request.getRequestURI(),request.getMethod());
        request.getRequestedSessionId();
        if(request.getRequestURI().equals("/api/v1/saveProduct")){
            logger.info("Product Data is Fetching",request.getRequestURI(),request.getMethod());
        }
        return true;
    }
    @Override
    @Async("AsyncExecutor")
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle is invoked...{}: {}",request.getRequestURI(),request.getMethod());
        if(response.getStatus()==201){
            EventLog eventLog=new EventLog(response.getHeader("X-Response-ID"),Long.parseLong((response.getHeader("createdTime"))),Instant.now().getEpochSecond(),null, request.getMethod(), response.getStatus(),(Instant.now().getEpochSecond()-Long.parseLong((response.getHeader("createdTime")))));
            logger.info("Processed SuccessFully ID: {}",response.getHeader("X-Response-ID"));
            logger.info("Event Completed for ID {} ,{}",response.getHeader("X-Response-ID"),eventLog);
            eventLogService.saveEvent(eventLog);
        }
        else{
            logger.debug("Processed Failed ID: {}",response.getHeader("X-Response-ID"));
        }
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
