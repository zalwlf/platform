package com.zalwlf.common.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * platform
 * <p>日志记录拦截</p>
 *<p>aop</p>
 * @author : lcq
 * @date : 2020-09-05 15:40
 **/
@Log4j2
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* com.zalwlf.controller.*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        log.info("-------------start log record------------");
    }

    @AfterReturning(value = "webLog()",returning = "result")
    public void doAfterReturning(Object result){
        log.info("-------------return log record------------");
    }

    @After("webLog()")
    public void doAfter() {
        log.info("-------------end log record------------");
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("-------------around log record start------------");
        Object result = joinPoint.proceed();
        log.info("-------------around log record end------------");
        return result;
    }

    @AfterThrowing(value = "webLog()",throwing = "ex")
    public void doAfterThrowing(Throwable ex){
        log.error("aspect exception record:"+ex.getMessage());
    }

}
