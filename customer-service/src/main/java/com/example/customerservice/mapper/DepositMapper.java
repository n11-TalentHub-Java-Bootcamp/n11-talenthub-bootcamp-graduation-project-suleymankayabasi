package com.example.customerservice.mapper;

import com.example.customerservice.dto.DepositDTO;
import com.example.customerservice.model.Deposit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DepositMapper {

    DepositMapper INSTANCE = Mappers.getMapper(DepositMapper.class);

    @Mapping(source = "customerId",target = "customer.id")
    Deposit convertDepositDTOtoDeposit(DepositDTO depositDTO);

    @Mapping(source = "customer.id",target = "customerId")
    DepositDTO convertDepositToDepositDTO(Deposit deposit );

    List<Deposit> convertDepositDTOListToDepositList(List<DepositDTO> depositDTOList);

    List<DepositDTO> convertDepositListToDepositDTOList(List<Deposit> depositList);
}
