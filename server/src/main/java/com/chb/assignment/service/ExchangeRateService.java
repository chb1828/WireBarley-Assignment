package com.chb.assignment.service;

import com.chb.assignment.service.dto.ApiResultDTO;
import com.chb.assignment.service.dto.ExchangeParamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private final WebClient webClient;

    /**
     * 외부 api로부터 Data를 가져옵니다.
     * @return
     */
    public double getData(ExchangeParamDTO parameterDTO) {
        Mono<ApiResultDTO> response = webClient.get()
                .retrieve()
                .bodyToMono(ApiResultDTO.class);
        ApiResultDTO apiResult = response.block();
        return getValue(apiResult,parameterDTO);
    }

    private double getValue(ApiResultDTO apiResultDTO, ExchangeParamDTO paramDTO) {
        double result = 0.0;
        switch (paramDTO) {
            case KRW: {
                result = apiResultDTO.getQuotes().getUsdKrw();
                break;
            }
            case JPY: {
                result = apiResultDTO.getQuotes().getUsdJpy();
                break;
            }
            case PHP: {
                result = apiResultDTO.getQuotes().getUsdPhp();
                break;
            }
        }
        return result;
    }
}
