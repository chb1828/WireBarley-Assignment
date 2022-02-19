package com.chb.assignment.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiExceptionResponse {
    private String errorCode;
    private String errorMessage;

    public ApiExceptionResponse(String errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
