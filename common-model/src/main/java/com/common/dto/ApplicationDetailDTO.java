package com.common.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ApplicationDetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long nationalId;
    private LocalDate createdAt;
    private boolean approvalStatus;
    private Double creditLimit;

}
