package com.nhnacademy.resident.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResidentModifyRequest {
    private String name;

    private String registrationNumber;

    private String genderCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss")
    private LocalDateTime birthDate;

    private String birthPlaceCode;

    private String registrationBaseAddress;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss")
    private LocalDateTime deathDate;

    private String deathPlaceCode;

    private String deathPlaceAddress;
}
