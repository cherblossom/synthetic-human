package ru.vasilev.starter.audit;

import ru.vasilev.starter.model.AuditRecord;

public interface AuditSink {
    void send(AuditRecord auditRecord);
}