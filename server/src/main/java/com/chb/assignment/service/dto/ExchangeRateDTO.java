package com.chb.assignment.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExchangeRateDTO {

    @JsonProperty("USDKRW")
    private double usdKrw;

    @JsonProperty("USDJPY")
    private double usdJpy;

    @JsonProperty("USDPHP")
    private double usdPhp;
}
