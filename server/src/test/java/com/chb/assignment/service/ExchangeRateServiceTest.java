package com.chb.assignment.service;

import com.chb.assignment.service.dto.ApiResultDTO;
import com.chb.assignment.service.dto.ExchangeParamDTO;
import com.chb.assignment.service.dto.ExchangeRateDTO;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateServiceTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS) // Deep Stub
    private WebClient webClient;

    @InjectMocks
    private ExchangeRateService exchangeRateService;

    @Test
    public void test() {

        //given
        ExchangeParamDTO param = ExchangeParamDTO.JPY;

        double expectResult = 100.0;

        ExchangeRateDTO exchangeRateDTO = ExchangeRateDTO.builder()
                .usdJpy(100.0)
                .usdKrw(1000.0)
                .usdPhp(10.0)
                .build();

        ApiResultDTO apiResult = ApiResultDTO.builder()
                .success(true)
                .quotes(exchangeRateDTO)
                .build();


        WebClient.RequestHeadersUriSpec requestHeadersUriSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.ResponseSpec responseSpec = Mockito.mock(WebClient.ResponseSpec.class);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(ApiResultDTO.class)).thenReturn(Mono.just(apiResult));

        //when
        double result = exchangeRateService.getData(param);

        //then
        assertEquals(result,expectResult);
    }
}
