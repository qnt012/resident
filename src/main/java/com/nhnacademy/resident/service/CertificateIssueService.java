package com.nhnacademy.resident.service;

import com.nhnacademy.resident.domain.dto.CertificateIssueDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CertificateIssueService {
    Page<CertificateIssueDto> getCertificateIssues(Long serialNum, Pageable pageable);
}
