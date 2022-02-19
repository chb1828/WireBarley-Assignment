package com.chb.assignment.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExchangeParamDTO {
    KRW("krw"),JPY("jpy"),PHP("php");

    final String name;
}
