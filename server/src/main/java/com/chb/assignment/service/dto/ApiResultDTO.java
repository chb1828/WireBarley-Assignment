package com.chb.assignment.service.dto;

import lombok.Data;

@Data
public class ApiResultDTO {

    private boolean success;
    private ExchangeRateDTO quotes;
}
