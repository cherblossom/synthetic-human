package ru.vasilev.starter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class StarterConfig {

    @Value("${queue.max-size}")
    private int maxQueueSize;

    public int getMaxQueueSize() {
        return maxQueueSize;
    }
}