package com.nhnacademy.resident.service.impl;

import com.nhnacademy.resident.domain.FamilyRelationshipCertificateDto;
import com.nhnacademy.resident.repository.CertificateIssueRepository;
import com.nhnacademy.resident.service.FamilyRelationshipCertificateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultFamilyRelationshipCertificateService implements FamilyRelationshipCertificateService {
    private final CertificateIssueRepository certificateIssueRepository;

    public DefaultFamilyRelationshipCertificateService(CertificateIssueRepository certificateIssueRepository) {
        this.certificateIssueRepository = certificateIssueRepository;
    }

    @Override
    public List<FamilyRelationshipCertificateDto> getFamilyRelationshipCertificate(Long serialNumber) {
        return certificateIssueRepository.findFamilyRelationshipCertificate(serialNumber);
    }
}
