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
public class CustomerDTO {

    private Long id ;
    private String name;
    private String lastName;
    private Long nationalId;
    private Double income;
    private String phoneNumber;
    private LocalDate birthDay;
    private Double creditScore;
    private LocalDate updateDate;
}
