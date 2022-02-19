package com.chb.assignment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {

    //에러의 code는 임의로 만든 코드입니다.
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "error_101","서버 에러입니다."),
    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "error_102","잘못된 요청입니다.");

    private final HttpStatus status;
    private String code;
    private String message;

}
