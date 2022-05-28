package com.nhnacademy.resident.service.impl;

import com.nhnacademy.resident.domain.CertificateIssueDto;
import com.nhnacademy.resident.repository.CertificateIssueRepository;
import com.nhnacademy.resident.service.CertificateIssueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCertificateIssueService implements CertificateIssueService {
    private final CertificateIssueRepository certificateIssueRepository;

    public DefaultCertificateIssueService(CertificateIssueRepository certificateIssueRepository) {
        this.certificateIssueRepository = certificateIssueRepository;
    }

    @Override
    public List<CertificateIssueDto> getCertificateIssues(Long serialNum) {
        return certificateIssueRepository.findAllByResidentSerialNumber(serialNum);
    }
}
