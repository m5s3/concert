package com.concert.interfaces.api.common;

import com.concert.support.error.CoreException;
import com.concert.support.error.ErrorType;
import com.concert.support.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CoreException.class)
    public ResponseEntity<ApiResponse<?>> handleCoreException(CoreException e) {
        switch (e.getErrorType().getLogLevel()) {
            case ERROR -> log.error("CoreException : {}", e.getMessage(), e);
            case WARN -> log.warn("CoreException : {}", e.getMessage(), e);
            default -> log.info("CoreException : {}", e.getMessage(), e);
        }
        return new ResponseEntity<>(ApiResponse.error(e.getErrorType(), e.getData()), e.getErrorType().getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
        log.error("Exception : {}", e.getMessage(), e);
        return new ResponseEntity<>(ApiResponse.error(ErrorType.DEFAULT_ERROR), ErrorType.DEFAULT_ERROR.getStatus());
    }
}
