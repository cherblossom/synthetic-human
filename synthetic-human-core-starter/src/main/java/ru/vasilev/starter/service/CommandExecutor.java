package ru.vasilev.starter.service;

import ru.vasilev.starter.model.Command;

public interface CommandExecutor {

    void execute(Command command);
}