package com.nhnacademy.resident.service;

import com.nhnacademy.resident.domain.dto.FamilyCompositionDto;
import com.nhnacademy.resident.domain.dto.FamilyRelationshipCertificateDto;

import java.util.List;

public interface FamilyRelationshipCertificateService {
    FamilyRelationshipCertificateDto getFamilyRelationshipCertificate(Long serialNumber);
    List<FamilyCompositionDto> getFamilyCompositions(Long serialNumber);
}
