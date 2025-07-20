package ru.vasilev.starter.service;

import ru.vasilev.starter.model.Command;

public interface CommandQueueService {

    void addToQueue(Command command);

    int getQueueSize();
}