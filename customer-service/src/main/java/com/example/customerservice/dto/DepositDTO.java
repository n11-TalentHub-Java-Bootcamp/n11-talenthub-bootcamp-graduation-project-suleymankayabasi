package com.example.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositDTO {

    private Long id;
    private String name;
    private LocalDate date;
    private Double depositAmount;
    private Long customerId;
}
