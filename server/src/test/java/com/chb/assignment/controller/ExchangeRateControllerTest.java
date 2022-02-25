package com.chb.assignment.controller;

import com.chb.assignment.service.ExchangeRateService;
import com.chb.assignment.service.dto.ExchangeParamDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateControllerTest {


    @Mock
    ExchangeRateService service;

    @InjectMocks
    private ExchangeRateController controller;

    @BeforeEach
    void setMockOutput() {
        ExchangeParamDTO param = ExchangeParamDTO.JPY;

        given(service.getData(param)).willReturn(100.0);
    }

    @Test
    public void getExchangeRate() {
        ExchangeParamDTO param = ExchangeParamDTO.JPY;

        ResponseEntity<Object> response = controller.getExchangeRate(param);
        assertEquals(response.getBody().toString(),"100.0");
    }


}
