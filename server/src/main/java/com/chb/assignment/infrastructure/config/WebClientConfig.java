package com.chb.assignment.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final ApiProperties apiProperties;
    private final String URL = "http://api.currencylayer.com/live";

    @Bean
    public WebClient webClient() {
        WebClient webClient = WebClient.builder()
                .baseUrl(URL+"?access_key="+apiProperties.getAccessKey())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return webClient;
    }
}
