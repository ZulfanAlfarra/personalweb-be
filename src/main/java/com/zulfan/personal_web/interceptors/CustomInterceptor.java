package com.zulfan.personal_web.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Component
public class CustomInterceptor implements HandlerInterceptor {
    private static final Logger logger = Logger.getLogger(CustomInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        logger.info("Pre handle method is calling");
        logger.info("Request URL: " + request.getRequestURI());
        logger.info("Request method: " + request.getMethod());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
        logger.info("Post handle method is calling");
        logger.info("Response status: " + response.getStatus());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{
        logger.info("After completion method i calling ");
        if(ex != null){
            logger.severe("Exception: " + ex.getMessage());
        }
    }
}

