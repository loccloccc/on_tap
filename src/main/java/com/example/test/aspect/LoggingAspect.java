package com.example.test.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    // Chỉ log các method create/update
    @Pointcut(
            "execution(* com.example.test.service.impl.MedicationServiceImpl.saveMedication(..)) || " +
                    "execution(* com.example.test.service.impl.MedicationServiceImpl.putMedication(..)) || " +
                    "execution(* com.example.test.service.impl.MedicationServiceImpl.patchMedication(..))"
    )
    public void medicationMethods() {
    }

    // Trước khi method chạy
    @Before("medicationMethods()")
    public void logBefore(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();

        log.info("Method {} bắt đầu chạy", methodName);
    }

    // Sau khi chạy thành công
    @AfterReturning(
            pointcut = "medicationMethods()",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {

        String methodName = joinPoint.getSignature().getName();

        log.info("Method {} chạy thành công", methodName);

        log.info("Kết quả trả về: {}", result);
    }

    // Khi có lỗi
    @AfterThrowing(
            pointcut = "medicationMethods()",
            throwing = "ex"
    )
    public void logError(JoinPoint joinPoint, Exception ex) {

        String methodName = joinPoint.getSignature().getName();

        log.error("Method {} bị lỗi", methodName);

        log.error("Error: {}", ex.getMessage());
    }
}