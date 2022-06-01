package com.nhnacademy.resident.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BirthDeathReportModifyRequest {
    private String typeCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private LocalDate reportDate;

    private String birthReportQualifyCode;

    private String deathReportQualifyCode;

    private String email;

    private String phone;
}
