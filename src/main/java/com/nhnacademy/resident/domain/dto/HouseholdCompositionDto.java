package com.nhnacademy.resident.domain.dto;

import java.time.LocalDate;

public interface HouseholdCompositionDto {
    String getRelationshipCode();
    String getResidentName();
    String getResidentRegistrationNumber();
    LocalDate getReportDate();
    String getChangeReason();
}
