package com.chb.assignment.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionAdvice {

    /**
     * 모든 Exception을 받는 핸들러를 생성합니다.
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiExceptionResponse> exceptionHandler(final Exception e) {
        return ResponseEntity
                .status(ExceptionEnum.INTERNAL_SERVER_ERROR.getStatus())
                .body(ApiExceptionResponse.builder()
                        .errorCode(ExceptionEnum.INTERNAL_SERVER_ERROR.getCode())
                        .errorMessage(e.getMessage())
                        .build());
    }

}
