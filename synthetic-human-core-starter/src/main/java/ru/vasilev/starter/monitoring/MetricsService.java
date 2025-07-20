package ru.vasilev.starter.monitoring;

public interface MetricsService {

    void incrementCommandExecuted(String author);

    int getQueueSize();
}