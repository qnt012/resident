package com.nhnacademy.resident.repository.impl;

import com.nhnacademy.resident.domain.FamilyCompositionDto;
import com.nhnacademy.resident.domain.FamilyRelationshipCertificateDto;
import com.nhnacademy.resident.entity.FamilyRelationship;
import com.nhnacademy.resident.entity.QFamilyRelationship;
import com.nhnacademy.resident.repository.custom.FamilyRelationshipRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class FamilyRelationshipRepositoryImpl extends QuerydslRepositorySupport implements FamilyRelationshipRepositoryCustom {
    public FamilyRelationshipRepositoryImpl() {
        super(FamilyRelationship.class);
    }

    @Override
    public List<FamilyCompositionDto> findFamilyCompositions(Long serialNumber) {
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;

        JPQLQuery query = from(familyRelationship);
        query.innerJoin(familyRelationship.familyResident);
        query.where(familyRelationship.baseResident.serialNumber.eq(serialNumber));

        query.select(
                Projections.bean(FamilyCompositionDto.class,
                        familyRelationship.relationshipCode,
                        familyRelationship.familyResident.name,
                        familyRelationship.familyResident.birthDate,
                        familyRelationship.familyResident.registrationNumber,
                        familyRelationship.familyResident.genderCode
                )
        );
        return query.fetch();
    }
}
