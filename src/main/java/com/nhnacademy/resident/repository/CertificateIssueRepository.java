package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.domain.dto.CertificateIssueDto;
import com.nhnacademy.resident.entity.CertificateIssue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long> {
    List<CertificateIssueDto> findAllByResidentSerialNumber(Long serialNumber);
}
