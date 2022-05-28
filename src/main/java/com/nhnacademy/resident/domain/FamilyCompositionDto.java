package com.nhnacademy.resident.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FamilyCompositionDto {
    private String relationshipCode;
    private String name;
    private LocalDate birthDate;
    private String registrationNumber;
    private String genderCode;
}
