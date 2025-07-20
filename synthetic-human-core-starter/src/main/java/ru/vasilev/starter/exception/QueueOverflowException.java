package ru.vasilev.starter.exception;

public class QueueOverflowException extends RuntimeException {

    private final String errorCode;

    public QueueOverflowException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}