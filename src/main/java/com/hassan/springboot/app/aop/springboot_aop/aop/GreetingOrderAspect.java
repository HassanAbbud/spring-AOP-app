package com.hassan.springboot.app.aop.springboot_aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class GreetingOrderAspect {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* com.hassan.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    private void greetingOrderLoggerPointcut(){};

    //Performs logic before execution of any methods in GreetingService
    @Before("greetingOrderLoggerPointcut()")
    public void loggerBefore(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Before Order " + method + " with arguments " + args);
    }

    //Performs logic after execution of any methods in GreetingService regardless of errors
    @After("greetingOrderLoggerPointcut()")
    public void loggerAfter(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("After Order " + method + " with arguments " + args);
    }
}
