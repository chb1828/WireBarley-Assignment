package com.chb.assignment.service;

import com.chb.assignment.service.dto.ApiResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private final WebClient webClient;

    public ApiResultDTO getData() {
        Mono<ApiResultDTO> result = webClient.get()
                .retrieve()
                .bodyToMono(ApiResultDTO.class);
        return result.block();
    }
}
