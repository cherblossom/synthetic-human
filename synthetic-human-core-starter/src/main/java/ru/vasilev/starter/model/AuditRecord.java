package ru.vasilev.starter.model;

import java.time.LocalDateTime;
import java.util.Map;


public class AuditRecord {

    private String methodName;
    private Map<String, Object> parameters;
    private Object result;
    private LocalDateTime timestamp;

    public AuditRecord(String methodName, Map<String, Object> parameters, Object result, LocalDateTime timestamp) {
        this.methodName = methodName;
        this.parameters = parameters;
        this.result = result;
        this.timestamp = timestamp;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}