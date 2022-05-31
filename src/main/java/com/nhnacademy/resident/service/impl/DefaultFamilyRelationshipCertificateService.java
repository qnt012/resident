package com.nhnacademy.resident.service.impl;

import com.nhnacademy.resident.domain.dto.FamilyCompositionDto;
import com.nhnacademy.resident.domain.dto.FamilyRelationshipCertificateDto;
import com.nhnacademy.resident.entity.CertificateIssue;
import com.nhnacademy.resident.entity.Resident;
import com.nhnacademy.resident.exception.ResidentNotFoundException;
import com.nhnacademy.resident.repository.CertificateIssueRepository;
import com.nhnacademy.resident.repository.FamilyRelationshipRepository;
import com.nhnacademy.resident.repository.ResidentRepository;
import com.nhnacademy.resident.service.FamilyRelationshipCertificateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class DefaultFamilyRelationshipCertificateService implements FamilyRelationshipCertificateService {
    private final ResidentRepository residentRepository;
    private final CertificateIssueRepository certificateIssueRepository;
    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final Random random = new Random();

    public DefaultFamilyRelationshipCertificateService(ResidentRepository residentRepository, CertificateIssueRepository certificateIssueRepository, FamilyRelationshipRepository familyRelationshipRepository) {
        this.residentRepository = residentRepository;
        this.certificateIssueRepository = certificateIssueRepository;
        this.familyRelationshipRepository = familyRelationshipRepository;
    }

    @Override
    public FamilyRelationshipCertificateDto getFamilyRelationshipCertificate(Long serialNumber) {
        Optional<Resident> resident = residentRepository.findById(serialNumber);
        if (resident.isEmpty()) throw new ResidentNotFoundException();

        CertificateIssue certificateIssue = new CertificateIssue(1234567800000000L + random.nextInt(100000000), resident.get(),"가족관계증명서", LocalDate.now());
        certificateIssueRepository.save(certificateIssue);
        return new FamilyRelationshipCertificateDto(certificateIssue.getIssueDate(), certificateIssue.getConfirmationNumber(), resident.get().getRegistrationBaseAddress());
    }

    @Override
    public List<FamilyCompositionDto> getFamilyCompositions(Long serialNumber) {
        return familyRelationshipRepository.findFamilyCompositions(serialNumber);
    }
}
