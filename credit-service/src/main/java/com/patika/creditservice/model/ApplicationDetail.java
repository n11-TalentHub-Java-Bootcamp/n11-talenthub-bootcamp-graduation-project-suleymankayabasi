package com.patika.creditservice.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Application_Details")
@Builder
public class ApplicationDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long nationalId;
    private LocalDate createdAt;
    private boolean approvalStatus;
    private Double creditLimit;
    private Double creditScore;
}
