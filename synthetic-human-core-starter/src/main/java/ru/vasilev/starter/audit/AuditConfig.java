package ru.vasilev.starter.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import ru.vasilev.starter.model.AuditRecord;

@Configuration
public class AuditConfig {
    private static final Logger logger = LoggerFactory.getLogger(AuditConfig.class);
    @Value("${audit.mode:console}")
    private String auditMode;
    @Value("${audit.kafka.topic:audit-topic}")
    private String kafkaTopic;


    // Не понял как реализовать внедрения по другому, тут один бин не внедряется в зависимости от аудит мода в
    // проперти файле
    @Bean
    public AuditSink auditSink(@Autowired(required = false) KafkaTemplate<String, AuditRecord> kafkaTemplate,
                               @Autowired ConsoleAuditSink consoleAuditSink) {
        if (kafkaTemplate != null && "kafka".equalsIgnoreCase(auditMode)) {
            logger.info("Настройка режима аудита: Kafka с топиком {}", kafkaTopic);
            return new KafkaAuditSink(kafkaTemplate, kafkaTopic);
        } else {
            logger.info("Настройка режима аудита: Консоль");
            return consoleAuditSink;
        }
    }
}