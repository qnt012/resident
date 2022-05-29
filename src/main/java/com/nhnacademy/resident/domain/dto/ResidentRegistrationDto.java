package com.nhnacademy.resident.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ResidentRegistrationDto {
    private LocalDate issueDate;
    private Long confirmationNumber;
    private String name;
    private String compositionReasonCode;
    private LocalDate compositionDate;
}
