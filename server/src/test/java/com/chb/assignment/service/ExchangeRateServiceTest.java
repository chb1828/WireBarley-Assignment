package com.chb.assignment.service;

import com.chb.assignment.service.dto.ApiResultDTO;
import com.chb.assignment.service.dto.ExchangeParamDTO;
import com.chb.assignment.service.dto.ExchangeRateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateServiceTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS) // Deep Stub
    private WebClient webClient;

    @InjectMocks
    private ExchangeRateService exchangeRateService;

    @BeforeEach
    void setMockOutput() {
        ExchangeRateDTO exchangeRateDTO = new ExchangeRateDTO();
        exchangeRateDTO.setUsdJpy(100.0);
        exchangeRateDTO.setUsdKrw(1000.0);
        exchangeRateDTO.setUsdPhp(10.0);

        ApiResultDTO apiResult = new ApiResultDTO();
        apiResult.setSuccess(true);
        apiResult.setQuotes(exchangeRateDTO);

        WebClient.RequestHeadersUriSpec requestHeadersUriSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.ResponseSpec responseSpec = Mockito.mock(WebClient.ResponseSpec.class);
        given(webClient.get()).willReturn(requestHeadersUriSpec);
        given(requestHeadersUriSpec.retrieve()).willReturn(responseSpec);
        given(responseSpec.bodyToMono(ApiResultDTO.class)).willReturn(Mono.just(apiResult));

    }


    @Test
    public void test() {

        //given
        ExchangeParamDTO param = ExchangeParamDTO.JPY;

        double expectResult = 100.0;

        //when
        double result = exchangeRateService.getData(param);

        //then
        assertEquals(result,expectResult);
    }
}
