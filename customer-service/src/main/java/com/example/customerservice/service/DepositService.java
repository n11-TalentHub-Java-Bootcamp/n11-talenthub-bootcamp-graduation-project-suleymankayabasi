package com.example.customerservice.service;

import com.example.customerservice.dto.DepositDTO;

import java.util.List;

public interface DepositService {

    List<DepositDTO> findAllDeposit();
    List<DepositDTO> findAllDepositByCustomerId(Long id);
    DepositDTO save(DepositDTO depositDTO);
    DepositDTO update(Long id,DepositDTO depositDTO);
    void delete(Long id);


}
