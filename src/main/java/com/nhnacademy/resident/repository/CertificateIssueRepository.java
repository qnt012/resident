package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.domain.dto.CertificateIssueDto;
import com.nhnacademy.resident.entity.CertificateIssue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long> {
    Page<CertificateIssueDto> findAllByResidentSerialNumber(Long serialNumber, Pageable pageable);
}
