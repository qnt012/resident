package com.nhnacademy.resident.service;

import com.nhnacademy.resident.domain.dto.CertificateIssueDto;

import java.util.List;

public interface CertificateIssueService {
    List<CertificateIssueDto> getCertificateIssues(Long serialNum);
}
