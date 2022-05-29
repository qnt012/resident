package com.nhnacademy.resident.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HouseholdDto {
    private Long serialNumber;
    private String name;
    private String compositionReasonCode;
    private LocalDate compositionDate;
}
