package com.nhnacademy.resident.domain.dto;

import java.time.LocalDate;

public interface MovementAddressDto {
    String getLastAddressYn();
    String getAddress();
    LocalDate getPkReportDate();
}
