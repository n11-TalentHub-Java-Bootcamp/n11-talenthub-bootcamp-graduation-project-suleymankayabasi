package com.example.creditservice.mapper;

import com.example.creditservice.dto.CreditDTO;
import com.example.creditservice.model.Credit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CreditMapper {

    CreditMapper INSTANCE = Mappers.getMapper(CreditMapper.class);

    Credit convertCreditDTOtoCredit(CreditDTO creditDTO);

    CreditDTO convertCreditToCreditDTO(CreditDTO creditDTO);

    List<Credit> convertCreditDTOListToCreditList(List<CreditDTO> creditDTOList);

    List<CreditDTO> convertCreditListToCreditDTOList(List<Credit> creditList);
}
