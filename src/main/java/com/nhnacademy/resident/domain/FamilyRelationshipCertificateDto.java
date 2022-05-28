package com.nhnacademy.resident.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class FamilyRelationshipCertificateDto {
    private LocalDate issueDate;
    private Long confirmationNumber;
    private String registrationBaseAddress;
}
