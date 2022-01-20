package com.example.creditservice.service;

import com.example.creditservice.dto.CreditDTO;

import java.time.LocalDate;
import java.util.List;

public interface CreditService {

    Double calculateCreditAmount();

    CreditDTO checkCreditDetail(Long id, LocalDate birthDay);
    // Gerçekleştirilmiş bir kredi başvurusu sadece kimlik numarası ve doğum tarihi bilgisi ile sorgulanabilir.
    // Doğum tarihi ve kimlik bilgisi eşleşmezse sorgulanamamalıdır.
    // customer servisinden gelcek burdaki parametreler

    List<CreditDTO> findAllCredit();
    List<CreditDTO> findAllCreditById(Long id);
    //CreditDTO returnCreditResult(Customer customer);


}
