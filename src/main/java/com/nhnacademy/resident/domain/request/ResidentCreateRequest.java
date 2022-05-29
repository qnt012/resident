package com.nhnacademy.resident.domain.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResidentCreateRequest {
    private Long serialNumber;

    private String name;

    private String registrationNumber;

    private String genderCode;

    private LocalDateTime birthDate;

    private String brithPlaceCode;

    private String registrationBaseAddress;
}
