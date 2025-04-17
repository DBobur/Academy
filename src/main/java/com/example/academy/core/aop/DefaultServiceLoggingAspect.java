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
public class DefaultServiceLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(DefaultServiceLoggingAspect.class);

    // Metod bajarilish vaqtini log qiladi, faqat meaningful log
    @Around("execution(* com.example.academy.modules.*.service.*.*(..))")
    public Object logExecutionDetails(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringType().getSimpleName() + "." + signature.getName();
        Object[] args = joinPoint.getArgs();

        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - start;

            // Faqat CRUD yoki auth operatsiyalarga yozish (misol)
            if (methodName.contains("create") || methodName.contains("update") || methodName.contains("login") || methodName.contains("delete")) {
                logger.info("✅ {}({}) -> returned [{}] in {} ms",
                        methodName,
                        Arrays.toString(args),
                        result != null ? result.toString() : "null",
                        duration);
            }

            return result;
        } catch (Exception ex) {
            long duration = System.currentTimeMillis() - start;
            logger.error("❌ Exception in {}({}) after {} ms. Message: {}", methodName, Arrays.toString(args), duration, ex.getMessage(), ex);
            throw ex;
        }
    }
}
