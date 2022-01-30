package com.patika.customerservice.model;

import com.common.dto.DepositDetailDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Customer_Details")
public class CustomerDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
