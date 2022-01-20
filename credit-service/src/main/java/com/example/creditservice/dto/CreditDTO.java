package com.example.creditservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditDTO {

    private Long id;

    // todo: burası kullanıcı servisinden gelcek
    private Double creditScore;
    private LocalDate applicationDate;

    //todo: bunu enum yapıp @Enumerated(EnumType.STRING)
    private String creditResult;
    private Double creditAmount;
}
