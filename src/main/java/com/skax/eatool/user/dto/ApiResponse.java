package com.skax.eatool.user.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {
    private final T data;
    private final String message;
    private final boolean success;
    private final PageInfo pageInfo;

    private ApiResponse(T data, String message, boolean success, PageInfo pageInfo) {
        this.data = data;
        this.message = message;
        this.success = success;
        this.pageInfo = pageInfo;
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(data, "성공", true, null);
    }

    public static <T> ApiResponse<T> ok(T data, PageInfo pageInfo) {
        return new ApiResponse<>(data, "성공", true, pageInfo);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(null, message, false, null);
    }

    public static <T> ApiResponse<T> ok() {
        return new ApiResponse<>(null, "성공", true, null);
    }

    public static <T> ApiResponse<T> fail(HttpStatus status, ExceptionDto exceptionDto) {
        return new ApiResponse<>(null, exceptionDto.getMessage(), false, null);
    }
}
