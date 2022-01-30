package com.common.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DepositDetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long nationalId;
    private String name;
    private Double amount;

}
