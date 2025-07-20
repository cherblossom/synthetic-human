package ru.vasilev.starter.audit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vasilev.starter.model.AuditRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class AuditAspect {
    private final AuditSink auditSink;

    @Autowired
    public AuditAspect(AuditSink auditSink) {
        this.auditSink = auditSink;
    }

    @Around("@annotation(ru.vasilev.starter.audit.WeylandWatchingYou)")
    public Object auditMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        if (auditSink == null) {
            throw new IllegalStateException("AuditSink не инициализирован. Проверьте конфигурацию.");
        }

        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Map<String, Object> parameters = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            parameters.put("arg" + i, args[i]);
        }

        Object result = joinPoint.proceed();
        AuditRecord auditRecord = new AuditRecord(methodName, parameters, result, LocalDateTime.now());
        auditSink.send(auditRecord);
        return result;
    }
}