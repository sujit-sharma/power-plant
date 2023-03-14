package com.sujit.handler;

import com.sujit.exception.DomainViolationException;
import com.sujit.exception.ResourceNotFoundException;
import com.sujit.exception.Violation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j(topic = "POWERPLANT_EXCEPTION_HANDLER")
@RestControllerAdvice
public class PowerplantExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorListResponse> handleDomainViolationException(DomainViolationException exception) {
        ApiErrorListResponse response = createApiErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error");

        log.error("Validation Error :" + exception);
        for (Violation violation: exception.getViolations()) {
            response.addErrorItem(
                    ApiErrorResponse
                    .builder()
                    .violator(violation.getViolator())
                    .message(violation.getErrorMessage())
                    .build()
            );
        }
        return ResponseEntity.badRequest().body(response);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorListResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {

        log.error("Resource not found :: ", exception);
        return buildErrorResponse(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiErrorListResponse> handleAllUncaughtException(Exception exception) {

        log.error("Unknown error occurred", exception);
        return buildErrorResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ApiErrorListResponse> buildErrorResponse(Exception exception, HttpStatus status) {
        return ResponseEntity.status(status).body(createApiErrorResponse(status, exception.getMessage()));
    }

    private ApiErrorListResponse createApiErrorResponse(HttpStatus httpStatus, String message) {
        return ApiErrorListResponse
                .builder()
                .message(message)
                .build();
    }
}
