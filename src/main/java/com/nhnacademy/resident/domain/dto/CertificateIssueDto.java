package com.nhnacademy.resident.domain.dto;

import java.time.LocalDate;

public interface CertificateIssueDto {
    Long getConfirmationNumber();
    String getTypeCode();
    LocalDate getIssueDate();
}
