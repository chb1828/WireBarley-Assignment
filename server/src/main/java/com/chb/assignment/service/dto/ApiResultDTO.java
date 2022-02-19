package com.chb.assignment.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResultDTO {

    private boolean success;
    private ExchangeRateDTO quotes;
}
