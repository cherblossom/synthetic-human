package ru.vasilev.starter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.vasilev.starter.exception.InvalidCommandException;
import ru.vasilev.starter.exception.QueueOverflowException;
import ru.vasilev.starter.model.Command;
import ru.vasilev.starter.validation.CommandValidator;

@Service
public class CommandServiceImpl implements CommandService {
    private static final Logger logger = LoggerFactory.getLogger(CommandServiceImpl.class);
    private final CommandValidator validator;
    private final CommandQueueService queueService;
    private final CommandExecutor executor;

    public CommandServiceImpl(CommandValidator validator, CommandQueueService queueService, CommandExecutor executor) {
        this.validator = validator;
        this.queueService = queueService;
        this.executor = executor;
    }

    @Override
    public void submitCommand(Command command) {
        try {
            validator.validate(command);
            if (command.getPriority() == Command.Priority.CRITICAL) {
                executor.execute(command);
            } else if (command.getPriority() == Command.Priority.COMMON) {
                queueService.addToQueue(command);
            } else {
                throw new InvalidCommandException("INVALID_PRIORITY", "Недопустимый приоритет: " + command.getPriority());
            }
        } catch (InvalidCommandException e) {
            logger.error("Ошибка валидации команды: {}", e.getMessage());
            throw e;
        } catch (QueueOverflowException e) {
            logger.error("Очередь переполнена: {}", e.getMessage());
            throw e;
        }
    }
}