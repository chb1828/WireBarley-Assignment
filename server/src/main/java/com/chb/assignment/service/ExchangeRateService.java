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
        return getValueFromParameter(apiResult,parameterDTO);
    }

    /**
     * 외부 api 결과에서 필요한 값만 추출한다.
     * @param apiResultDTO 외부 api 결과값
     * @param paramDTO 가져올 파라미터
     * @return
     */
    private double getValueFromParameter (ApiResultDTO apiResultDTO, ExchangeParamDTO paramDTO) {
        double result = 0.0;
        switch (paramDTO) {
            case KRW: {
                result = getDecimalPoint(apiResultDTO.getQuotes().getUsdKrw());
                break;
            }
            case JPY: {
                result = getDecimalPoint(apiResultDTO.getQuotes().getUsdJpy());
                break;
            }
            case PHP: {
                result = getDecimalPoint(apiResultDTO.getQuotes().getUsdPhp());
                break;
            }
        }
        return result;
    }

    /**
     * 소수점 2개까지만 보여준다.
     * @param parameter
     * @return
     */
    private double getDecimalPoint(double parameter) {
        return Math.round(parameter * 100) / 100.0;
    }
}
