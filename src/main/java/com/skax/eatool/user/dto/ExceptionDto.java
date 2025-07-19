package com.skax.eatool.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExceptionDto {
    private final String message;
    private final String code;
    private final String details;
} 
