package ru.vasilev.starter.validation;

import ru.vasilev.starter.model.Command;


public interface CommandValidator {
    void validate(Command command);
}