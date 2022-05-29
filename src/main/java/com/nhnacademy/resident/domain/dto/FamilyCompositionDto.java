package com.nhnacademy.resident.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class FamilyCompositionDto {
    private String relationshipCode;
    private String name;
    private LocalDateTime birthDate;
    private String registrationNumber;
    private String genderCode;
}
