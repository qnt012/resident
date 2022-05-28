package com.nhnacademy.resident.repository.custom;

import com.nhnacademy.resident.domain.FamilyRelationshipCertificateDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CertificateIssueRepositoryCustom {
    List<FamilyRelationshipCertificateDto> findFamilyRelationshipCertificates(Long serialNumber);
}
