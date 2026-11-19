package com.devtiro.task.controller;

import com.devtiro.task.domain.dto.ErrorResponseDto;
import com.devtiro.task.exception.TaskNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handle MethodArgumentNotValidException
     * Exception is thrown when @Valid fails
     * @param ex the exception
     * @return standardized error response w/ 400 code
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(
            MethodArgumentNotValidException ex
    ) {
        // Get first validation error message
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Validation failed");

        // Create ErrorResponseDto using error msg
        ErrorResponseDto errorDto = new ErrorResponseDto(errorMessage);

        // Return ErrorResponseDto with HTTP 400 BAD REQUEST
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({TaskNotFoundException.class})
    public ResponseEntity<ErrorResponseDto> handleExceptions(TaskNotFoundException ex) {
        ErrorResponseDto errorDto = new ErrorResponseDto(
                String.format("Task with ID '%s' not found", ex.getId())
        );

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
