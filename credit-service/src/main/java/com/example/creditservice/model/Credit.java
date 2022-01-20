package com.example.creditservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Credits")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Credit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // todo: burası kullanıcı servisinden gelcek
    private Double creditScore;

    private LocalDate applicationDate;

    //todo: bunu enum yapıp @Enumerated(EnumType.STRING)
    private String creditResult;
    private Double creditAmount;

    //todo: bunların columnlarını filan doldurabilirisn
}
