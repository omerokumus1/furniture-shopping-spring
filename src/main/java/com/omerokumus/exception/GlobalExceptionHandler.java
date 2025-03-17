package com.omerokumus.exception;

import com.omerokumus.feature.user.exception.NotFoundException;
import com.omerokumus.feature.user.exception.ProductAlreadyInFavoritesException;
import com.omerokumus.feature.user.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<ApiError<String>> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ResponseEntity.badRequest().body(new ApiError<>(ex.getMessage()));
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ApiError<String>> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(404).body(new ApiError<>(ex.getMessage()));
    }

    @ExceptionHandler(value = ProductAlreadyInFavoritesException.class)
    public ResponseEntity<ApiError<String>> handleProductAlreadyInFavoritesException(ProductAlreadyInFavoritesException ex) {
        return ResponseEntity.badRequest().body(new ApiError<>(ex.getMessage()));
    }

}
