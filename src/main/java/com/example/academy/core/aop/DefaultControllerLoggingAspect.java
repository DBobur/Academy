package com.example.academy.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class DefaultControllerLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(DefaultControllerLoggingAspect.class);

    // for all controller apis
    @Around("execution(* com.example.academy.modules..controller..*(..))")
    public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = signature.getName();
        Object[] args = joinPoint.getArgs();

        logger.info("➡️  REQUEST: {}.{}() with arguments {}", className, methodName, Arrays.toString(args));

        long startTime = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - startTime;

            logger.info("✅ RESPONSE: {}.{}() returned: {} ({} ms)", className, methodName, result, duration);
            return result;
        } catch (Throwable ex) {
            long duration = System.currentTimeMillis() - startTime;
            logger.error("❌ ERROR in {}.{}() - {} ({} ms)", className, methodName, ex.getMessage(), duration, ex);
            throw ex;
        }
    }
}
