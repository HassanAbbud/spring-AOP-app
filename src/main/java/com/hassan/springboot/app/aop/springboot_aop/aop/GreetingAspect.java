package com.hassan.springboot.app.aop.springboot_aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //Performs logic before execution of any methods in GreetingService
    @Before("execution(* com.hassan.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void loggerBefore(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Before " + method + " with arguments " + args);
    }

    //Performs logic after execution of any methods in GreetingService regardless of errors
    @After("execution(* com.hassan.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void loggerAfter(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("After " + method + " with arguments " + args);
    }
    //Performs logic after execution of any methods that returned something in GreetingService
    @AfterReturning("execution(* com.hassan.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void loggerAfterReturning(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("After returning " + method + " with arguments " + args);
    }
    //Performs logic after an error has been thrown in any methods of GreetingService
    @AfterThrowing("execution(* com.hassan.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void loggerAfterThrowing(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("After throwing exception " + method + " with arguments " + args);
    }

    //Performs logic around the method as its being executed, can execute code before and after
    @Around("execution(* com.hassan.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable{
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        
        Object result = null;
        try {
            //Before execution
            logger.info("The method " + method + "() with parameters " + args);
            result = joinPoint.proceed();
            //After execution
            logger.info("The method " + method + "() returns: " + result);

            return result;
        } catch (Throwable e) {
            logger.error("Error in the call of method: " + method + "()");
            throw e;
        }
    }
}   
