package com.nhnacademy.resident.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResidentDto {
    private Long serialNumber;

    private String name;

    private String genderCode;

    private LocalDateTime birthDate;

    private String birthCode;

    private String deathCode;
}
