package ru.vasilev.starter.audit;

import ru.vasilev.starter.model.AuditRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class KafkaAuditSink implements AuditSink {
    private final KafkaTemplate<String, AuditRecord> kafkaTemplate;
    private final String topic;

    public KafkaAuditSink(KafkaTemplate<String, AuditRecord> kafkaTemplate,
                          @Value("${audit.kafka.topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void send(AuditRecord auditRecord) {
        if (kafkaTemplate != null) {
            kafkaTemplate.send(topic, auditRecord);
        } else {
            throw new IllegalStateException("KafkaTemplate не инициализирован. Проверьте конфигурацию Kafka.");
        }
    }
}