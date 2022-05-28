package com.nhnacademy.resident.domain;

import java.time.LocalDate;

public interface CertificateIssueDto {
    Long getConfirmationNumber();
    String getTypeCode();
    LocalDate getIssueDate();
}
