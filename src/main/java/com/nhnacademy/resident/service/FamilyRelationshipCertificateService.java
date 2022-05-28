package com.nhnacademy.resident.service;

import com.nhnacademy.resident.domain.FamilyRelationshipCertificateDto;

import java.util.List;
import java.util.Optional;

public interface FamilyRelationshipCertificateService {
    List<FamilyRelationshipCertificateDto> getFamilyRelationshipCertificate(Long serialNumber);
}
