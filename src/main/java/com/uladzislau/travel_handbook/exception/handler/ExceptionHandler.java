package com.uladzislau.travel_handbook.exception.handler;

import com.uladzislau.travel_handbook.exception.details.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class ExceptionHandler {

    private static final String RESOURCE_NOT_FOUND_EXCEPTION = "ResourceNotFoundException";

    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<ErrorDetails> handleException(RuntimeException e, WebRequest request) {
        LocalDateTime now = LocalDateTime.now();
        log.error("Error occurred at " + now + "due to", e);
        ErrorDetails errorDetails = ErrorDetails.builder()
                .time(now)
                .details(request.getDescription(false))
                .message(e.getMessage())
                .build();
        switch (e.getClass().getSimpleName()) {
            case RESOURCE_NOT_FOUND_EXCEPTION:
                return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

        }
    }
}
