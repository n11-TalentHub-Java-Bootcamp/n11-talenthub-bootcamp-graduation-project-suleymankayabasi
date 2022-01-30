package com.patika.customerservice.service;

import com.common.dto.CustomerDetailDTO;

import java.time.LocalDate;

public interface CustomerDetailService {

    CustomerDetailDTO save(CustomerDetailDTO customerDetailDTO);
    CustomerDetailDTO update(Long nationalId, CustomerDetailDTO customerDetailDTO);
    void delete(Long nationalId, LocalDate birthday);
    CustomerDetailDTO findByNationalIdAndBirthday(Long natioanlId,LocalDate date);
}
