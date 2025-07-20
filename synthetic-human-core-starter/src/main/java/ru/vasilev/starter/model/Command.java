package ru.vasilev.starter.model;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

public class Command {
    private String description;
    private Priority priority;
    private String author;
    private String time;

    public Command() {
    }

    public Command(String description, Priority priority, String author, String time) {
        this.description = description;
        this.priority = priority;
        this.author = author;
        this.time = time;
    }

    // Геттеры и сеттеры
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null && description.length() > 1000) {
            throw new IllegalArgumentException("Description must be max 1000 characters");
        }
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author != null && author.length() > 100) {
            throw new IllegalArgumentException("Author must be max 100 characters");
        }
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        if (time != null) {
            try {
                OffsetDateTime.parse(time); // Используем OffsetDateTime для поддержки ISO 8601 с Z
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Time must be in ISO 8601 format");
            }
        }
        this.time = time;
    }

    /**
     * Проверяет валидность команды.
     * @return true, если команда валидна, иначе выбрасывает исключение
     */
    public boolean isValid() {
        if (priority == null) throw new IllegalArgumentException("Priority must be non-null");
        return true; // Дополнительная проверка в сеттерах
    }


    /**
     * Перечисление, представляющее уровни приоритета команды.
     */
    public enum Priority {
        COMMON, CRITICAL
    }
}