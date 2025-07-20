package ru.vasilev.starter.validation;

import org.springframework.stereotype.Component;
import ru.vasilev.starter.exception.InvalidCommandException;
import ru.vasilev.starter.model.Command;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;


@Component
public class CommandValidatorImpl implements CommandValidator {
    private static final int MAX_DESCRIPTION_LENGTH = 1000;
    private static final int MAX_AUTHOR_LENGTH = 100;

    @Override
    public void validate(Command command) {
        if (command == null) {
            throw new InvalidCommandException("INVALID_COMMAND", "Команда не может быть null");
        }

        // Проверка описания
        if (command.getDescription() == null || command.getDescription().length() > MAX_DESCRIPTION_LENGTH) {
            throw new InvalidCommandException("INVALID_DESCRIPTION",
                    "Описание должно быть null или до " + MAX_DESCRIPTION_LENGTH + " символов");
        }

        // Проверка приоритета
        if (command.getPriority() == null) {
            throw new InvalidCommandException("INVALID_PRIORITY", "Приоритет не может быть null");
        }

        // Проверка автора
        if (command.getAuthor() == null || command.getAuthor().length() > MAX_AUTHOR_LENGTH) {
            throw new InvalidCommandException("INVALID_AUTHOR",
                    "Автор должен быть null или до " + MAX_AUTHOR_LENGTH + " символов");
        }

        // Проверка времени (ISO 8601 с Z или смещением)
        if (command.getTime() == null) {
            throw new InvalidCommandException("INVALID_TIME", "Время не может быть null");
        }

        try {
            OffsetDateTime.parse(command.getTime()); // <-- ISO 8601 with offset (e.g., 2025-07-18T14:30:00Z or +03:00)
        } catch (DateTimeParseException ex) {
            throw new InvalidCommandException("INVALID_TIME",
                    "Время должно быть в формате ISO 8601, например: 2025-07-18T14:30:00Z");
        }
    }
}