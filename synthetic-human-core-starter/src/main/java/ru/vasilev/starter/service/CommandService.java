package ru.vasilev.starter.service;

import ru.vasilev.starter.model.Command;

public interface CommandService {
    void submitCommand(Command command);
}