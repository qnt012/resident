package com.nhnacademy.resident.service;

import com.nhnacademy.resident.domain.impl.FamilyCompositionDto;
import com.nhnacademy.resident.domain.impl.FamilyRelationshipCertificateDto;

import java.util.List;

public interface FamilyRelationshipCertificateService {
    FamilyRelationshipCertificateDto getFamilyRelationshipCertificate(Long serialNumber);
    List<FamilyCompositionDto> getFamilyCompositions(Long serialNumber);
}
