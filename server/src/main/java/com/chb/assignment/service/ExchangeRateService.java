package com.chb.assignment.service;

import com.chb.assignment.service.dto.ApiResultDTO;
import com.chb.assignment.service.dto.ExchangeParamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private final WebClient webClient;
    //Api 실패를 대비해서 최근 값을 Map에 저장해 놓는다.
    private Map<String,Double> dataMap = new HashMap<>();

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
     * 자주 호출할 시에 실패하는 경우가 있어, 실패시 맵에 저장한 값을 가져온다.
     * @param apiResultDTO 외부 api 결과값
     * @param paramDTO 가져올 파라미터
     * @return
     */
    private double getValueFromParameter(ApiResultDTO apiResultDTO, ExchangeParamDTO paramDTO) {
        double result = 0.0;
        switch (paramDTO) {
            case KRW: {
                if(!apiResultDTO.isSuccess()) {
                    result = dataMap.get("usdKrw");
                }else {
                    result = getDecimalPoint(apiResultDTO.getQuotes().getUsdKrw());
                    dataMap.put("usdKrw",result);
                }
                break;
            }
            case JPY: {
                if(!apiResultDTO.isSuccess()) {
                    result = dataMap.get("usdJpy");
                }else {
                    result = getDecimalPoint(apiResultDTO.getQuotes().getUsdJpy());
                    dataMap.put("usdJpy",result);
                }
                break;
            }
            case PHP: {
                if(!apiResultDTO.isSuccess()) {
                    result = dataMap.get("usdPhp");
                }else {
                    result = getDecimalPoint(apiResultDTO.getQuotes().getUsdPhp());
                    dataMap.put("usdPhp",result);
                }
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
