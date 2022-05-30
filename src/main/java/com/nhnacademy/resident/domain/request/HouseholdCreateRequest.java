package com.nhnacademy.resident.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HouseholdCreateRequest {
    private Long serialNumber;

    private Long residentSerialNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private LocalDate compositionDate;

    private String compositionReasonCode;

    private String currentAddress;
}
