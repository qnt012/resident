package com.nhnacademy.resident.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FamilyRelationshipCertificateDto {
    private LocalDate issueDate;
    private Long confirmationNumber;
    private String registrationBaseAddress;
}
