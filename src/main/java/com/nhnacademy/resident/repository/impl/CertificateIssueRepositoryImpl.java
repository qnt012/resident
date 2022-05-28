package com.nhnacademy.resident.repository.impl;

import com.nhnacademy.resident.domain.FamilyRelationshipCertificateDto;
import com.nhnacademy.resident.entity.CertificateIssue;
import com.nhnacademy.resident.entity.QCertificateIssue;
import com.nhnacademy.resident.repository.custom.CertificateIssueRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CertificateIssueRepositoryImpl extends QuerydslRepositorySupport implements CertificateIssueRepositoryCustom {
    public CertificateIssueRepositoryImpl() {
        super(CertificateIssue.class);
    }

    @Override
    public List<FamilyRelationshipCertificateDto> findFamilyRelationshipCertificates(Long serialNumber) {
        QCertificateIssue certificateIssue = QCertificateIssue.certificateIssue;

        JPQLQuery query = from(certificateIssue);
        query.innerJoin(certificateIssue.resident);
        query.where(certificateIssue.resident.serialNumber.eq(serialNumber));
        query.where(certificateIssue.typeCode.eq("가족관계증명서"));
        query.orderBy(certificateIssue.issueDate.desc());

        query.select(
                Projections.bean(FamilyRelationshipCertificateDto.class,
                        certificateIssue.issueDate,
                        certificateIssue.confirmationNumber,
                        certificateIssue.resident.registrationBaseAddress
                        )
        );
        return query.fetch();
    }
}
