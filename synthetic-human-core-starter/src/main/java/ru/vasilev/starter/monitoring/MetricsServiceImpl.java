package ru.vasilev.starter.monitoring;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;
import ru.vasilev.starter.service.CommandQueueService;


@Service
public class MetricsServiceImpl implements MetricsService {
    private final MeterRegistry meterRegistry;
    private final CommandQueueService queueService;

    public MetricsServiceImpl(MeterRegistry meterRegistry, CommandQueueService queueService) {
        this.meterRegistry = meterRegistry;
        this.queueService = queueService;
        Gauge.builder("queue_size", queueService, CommandQueueService::getQueueSize)
                .description("Current number of tasks in the command queue")
                .register(meterRegistry);
    }

    @Override
    public void incrementCommandExecuted(String author) {
        meterRegistry.counter("commands_executed_total", "author", author).increment();
    }

    @Override
    public int getQueueSize() {
        return queueService.getQueueSize();
    }
}