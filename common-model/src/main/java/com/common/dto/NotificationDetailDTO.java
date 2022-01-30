package com.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String message;
    private LocalDate createdAt;
    private boolean status;

}
