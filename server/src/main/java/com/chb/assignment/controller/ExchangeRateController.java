package com.chb.assignment.controller;

import com.chb.assignment.service.ExchangeRateService;
import com.chb.assignment.service.dto.ApiResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateService service;

    @GetMapping
    public ResponseEntity<Object> getExchangeRate() {
        ApiResultDTO result = service.getData();
        return ResponseEntity.status(HttpStatus.OK).body(result.getQuotes());
    }
}
