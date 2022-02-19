package com.chb.assignment.infrastructure.converter;

import com.chb.assignment.service.dto.ExchangeParamDTO;
import org.springframework.core.convert.converter.Converter;

/**
 * 소문자 String을 Enum으로 변경하기 위한 converter를 생성한다
 */
public class StringToEnumConverter implements Converter<String, ExchangeParamDTO> {

    @Override
    public ExchangeParamDTO convert(String source) {
        return ExchangeParamDTO.valueOf(source.toUpperCase());
    }
}
