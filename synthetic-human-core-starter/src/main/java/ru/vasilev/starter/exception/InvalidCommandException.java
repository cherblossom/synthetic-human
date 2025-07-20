package ru.vasilev.starter.exception;

public class InvalidCommandException extends RuntimeException {

    private final String errorCode;

    public InvalidCommandException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}