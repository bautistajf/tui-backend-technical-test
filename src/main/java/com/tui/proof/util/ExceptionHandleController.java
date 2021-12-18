package com.tui.proof.util;

import com.tui.proof.exception.CookedException;
import com.tui.proof.exception.UserLoginException;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ExceptionHandleController {

    @ExceptionHandler(UserLoginException.class)
    public ResponseEntity<ErrorResponse> handleLoginException(UserLoginException e) {
        ErrorDescription errorDescription = new ErrorDescription("Authentication", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(List.of(errorDescription)), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse(extractErrorFieldsWithMessages(ex.getBindingResult()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        ErrorDescription errorDescription = new ErrorDescription("Object", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(List.of(errorDescription)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CookedException.class)
    public ResponseEntity<ErrorResponse> handleCookedException(NotFoundException e) {
        ErrorDescription errorDescription = new ErrorDescription("Object", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(List.of(errorDescription)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleObjectNotFoundException(RuntimeException e) {
        log.error(e.getMessage(), e);
        ErrorDescription errorDescription = new ErrorDescription("Server", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(List.of(errorDescription)), HttpStatus.NOT_FOUND);
    }

    private List<ErrorDescription> extractErrorFieldsWithMessages(BindingResult bindingResult) {
        return bindingResult
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ErrorDescription(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
    }
}
