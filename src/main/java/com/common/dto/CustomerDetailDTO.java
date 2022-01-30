package com.common.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CustomerDetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id ;
    private String name;
    private String lastName;
    private Long nationalId;
    private Double income;
    private String phoneNumber;
    private LocalDate birthDay;
    private Double creditScore;
    private DepositDetailDTO depositDetailDTO;

}
