package ru.vasilev.starter.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import ru.vasilev.starter.model.ErrorResponse;
import org.apache.kafka.common.KafkaException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(InvalidCommandException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalidCommandException(InvalidCommandException ex) {
        logger.warn("Invalid command: {}", ex.getMessage());
        return new ErrorResponse(ex.getErrorCode(), ex.getMessage(), "Недействительные данные команды");
    }

    @ExceptionHandler(QueueOverflowException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    @ResponseBody
    public ErrorResponse handleQueueOverflowException(QueueOverflowException ex) {
        logger.warn("Queue overflow: {}", ex.getMessage());
        return new ErrorResponse(ex.getErrorCode(), ex.getMessage(), "Очередь команд заполнена");
    }

    @ExceptionHandler(KafkaException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public ErrorResponse handleKafkaException(KafkaException ex) {
        logger.error("Kafka error: {}", ex.getMessage());
        return new ErrorResponse("KAFKA_ERROR", "Ошибка взаимодействия с Kafka", "Проверьте конфигурацию Kafka");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        logger.error("Invalid JSON input: {}", ex.getMessage());
        return new ErrorResponse("INVALID_JSON", "Некорректный JSON формат", ex.getMessage());

    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("Validation error: {}", ex.getMessage());
        return new ErrorResponse("VALIDATION_ERROR", ex.getMessage(), "Проверьте входные данные");

    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleGenericException(Exception ex) {
        logger.error("Unexpected error: ", ex);
        return new ErrorResponse("INTERNAL_ERROR", "Произошла неожиданная ошибка", "Обратитесь к администратору");
    }
}