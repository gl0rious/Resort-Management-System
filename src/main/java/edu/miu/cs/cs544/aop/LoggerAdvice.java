package edu.miu.cs.cs544.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class LoggerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAdvice.class);

    @Around("execution(* edu.miu.cs.cs544.service.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch clock = new StopWatch("");
        clock.start();
        Object object = joinPoint.proceed();
        clock.stop();

        String info = joinPoint.getSignature().toString();
        LOGGER.info("{} took {} milliseconds to execute", info, clock.getTotalTimeMillis());

        return object;
    }

}