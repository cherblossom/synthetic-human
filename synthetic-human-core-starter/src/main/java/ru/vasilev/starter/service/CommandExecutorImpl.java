package ru.vasilev.starter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.vasilev.starter.model.Command;
import ru.vasilev.starter.monitoring.MetricsService;

@Component
public class CommandExecutorImpl implements CommandExecutor {
    private static final Logger logger = LoggerFactory.getLogger(CommandExecutorImpl.class);
    private final MetricsService metricsService;

    @Autowired
    public CommandExecutorImpl(@Lazy MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @Override
    public void execute(Command command) {
        logger.info("Выполнение команды: Описание={}, Приоритет={}, Автор={}, Время={}",
                command.getDescription(), command.getPriority(), command.getAuthor(), command.getTime());
        metricsService.incrementCommandExecuted(command.getAuthor());
    }
}