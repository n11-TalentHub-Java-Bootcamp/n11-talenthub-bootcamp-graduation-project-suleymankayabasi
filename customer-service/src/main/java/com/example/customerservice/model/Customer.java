package com.example.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Customers")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false,length = 50)
    private String name;

    @Column(nullable = false,length = 50)
    private String lastName;

    @Column(nullable = false,length = 11,unique = true)
    private Long nationalId;

    @Column(nullable = false)
    private Double income;

    @Column(nullable = false,length = 11,unique = true)
    private String phoneNumber;

    private LocalDate birthDay;

    private Double creditScore;

    private LocalDate updateDate;


}
