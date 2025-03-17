package com.omerokumus.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ApiError<T> {
    private String id;
    private Date errorTime;
    private T error;

    public ApiError(T error) {
        this.id = UUID.randomUUID().toString();
        this.errorTime = new Date();
        this.error = error;
    }

    public static <T> ApiError<T> from(T errorMap) {
        return new ApiError<>(errorMap);
    }
}
