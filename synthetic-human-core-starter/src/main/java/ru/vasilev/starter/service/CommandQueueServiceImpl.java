package ru.vasilev.starter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.vasilev.starter.exception.QueueOverflowException;
import ru.vasilev.starter.model.Command;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Service
public class CommandQueueServiceImpl implements CommandQueueService {
    private static final Logger logger = LoggerFactory.getLogger(CommandQueueServiceImpl.class);
    private final ThreadPoolExecutor executor;
    private final CommandExecutor commandExecutor;
    private final int maxQueueSize;

    public CommandQueueServiceImpl(CommandExecutor commandExecutor, @Value("${queue.max-size}") int maxQueueSize) {
        this.commandExecutor = commandExecutor;
        this.maxQueueSize = maxQueueSize;
        this.executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(maxQueueSize));
    }

    @Override
    public void addToQueue(Command command) {
        if (executor.getQueue().size() >= maxQueueSize) {
            throw new QueueOverflowException("QUEUE_FULL", "Очередь команд заполнена");
        }
        executor.submit(() -> commandExecutor.execute(command));
    }

    @Override
    public int getQueueSize() {
        return executor.getQueue().size();
    }
}