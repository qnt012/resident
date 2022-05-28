package com.nhnacademy.resident.service;

import com.nhnacademy.resident.domain.FamilyCompositionDto;
import com.nhnacademy.resident.domain.FamilyRelationshipCertificateDto;

import java.util.List;

public interface FamilyRelationshipCertificateService {
    FamilyRelationshipCertificateDto getFamilyRelationshipCertificate(Long serialNumber);
    List<FamilyCompositionDto> getFamilyCompositions(Long serialNumber);
}
