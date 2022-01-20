package com.example.customerservice.service;

import com.example.customerservice.dto.CustomerDTO;
import com.example.customerservice.dto.DepositDTO;

import java.time.LocalDate;
import java.util.List;

public interface CustomerService {

    CustomerDTO update(Long id,CustomerDTO customerDTO);
    CustomerDTO save(CustomerDTO customerDTO);
    void deleteByCustomerID(Long id);
    List<CustomerDTO> findAllCustomer();
    CustomerDTO findByCustomerID(Long id);
    CustomerDTO checkValidCustomer(Long nationalId, LocalDate birthday);
    List<DepositDTO> findAllDepositByCustomerId(Long id);
}
