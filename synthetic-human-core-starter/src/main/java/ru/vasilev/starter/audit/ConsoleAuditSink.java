package ru.vasilev.starter.audit;

import ru.vasilev.starter.model.AuditRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ConsoleAuditSink implements AuditSink {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleAuditSink.class);

    @Override
    public void send(AuditRecord auditRecord) {
        logger.info("Аудит [{}]: Метод={}, Параметры={}, Результат={}, Время={}",
                LocalDateTime.now(), auditRecord.getMethodName(), auditRecord.getParameters(),
                auditRecord.getResult(), auditRecord.getTimestamp());
    }
}