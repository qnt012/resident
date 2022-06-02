package com.nhnacademy.resident.service.impl;

import com.nhnacademy.resident.domain.dto.CertificateIssueDto;
import com.nhnacademy.resident.repository.CertificateIssueRepository;
import com.nhnacademy.resident.service.CertificateIssueService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCertificateIssueService implements CertificateIssueService {
    private final CertificateIssueRepository certificateIssueRepository;

    public DefaultCertificateIssueService(CertificateIssueRepository certificateIssueRepository) {
        this.certificateIssueRepository = certificateIssueRepository;
    }

    @Override
    public Page<CertificateIssueDto> getCertificateIssues(Long serialNum, Pageable pageable) {
        return certificateIssueRepository.findAllByResidentSerialNumber(serialNum, pageable);
    }
}
