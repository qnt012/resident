package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.entity.CertificateIssue;
import com.nhnacademy.resident.repository.custom.CertificateIssueRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long>, CertificateIssueRepositoryCustom {
}
