package com.nhnacademy.resident.service.impl;

import com.nhnacademy.resident.domain.FamilyCompositionDto;
import com.nhnacademy.resident.domain.FamilyRelationshipCertificateDto;
import com.nhnacademy.resident.repository.CertificateIssueRepository;
import com.nhnacademy.resident.repository.FamilyRelationshipRepository;
import com.nhnacademy.resident.service.FamilyRelationshipCertificateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultFamilyRelationshipCertificateService implements FamilyRelationshipCertificateService {
    private final CertificateIssueRepository certificateIssueRepository;
    private final FamilyRelationshipRepository familyRelationshipRepository;

    public DefaultFamilyRelationshipCertificateService(CertificateIssueRepository certificateIssueRepository, FamilyRelationshipRepository familyRelationshipRepository) {
        this.certificateIssueRepository = certificateIssueRepository;
        this.familyRelationshipRepository = familyRelationshipRepository;
    }

    @Override
    public List<FamilyRelationshipCertificateDto> getFamilyRelationshipCertificate(Long serialNumber) {
        return certificateIssueRepository.findFamilyRelationshipCertificate(serialNumber);
    }

    @Override
    public List<FamilyCompositionDto> getFamilyCompositions(Long serialNumber) {
        return familyRelationshipRepository.findFamilyCompositions(serialNumber);
    }
}
