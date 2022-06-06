package com.nhnacademy.resident.repository.impl;

import com.nhnacademy.resident.domain.dto.ResidentDto;
import com.nhnacademy.resident.entity.QBirthDeathReport;
import com.nhnacademy.resident.entity.QHouseholdComposition;
import com.nhnacademy.resident.entity.QResident;
import com.nhnacademy.resident.repository.custom.ResidentRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import org.springframework.data.support.PageableExecutionUtils;

public class ResidentRepositoryImpl extends QuerydslRepositorySupport implements ResidentRepositoryCustom {
    public ResidentRepositoryImpl() {
        super(ResidentDto.class);
    }

    @Override
    public Page<ResidentDto> findResidents(Pageable pageable, Long householdSerialNumber) {
        QResident resident = QResident.resident;
        QBirthDeathReport birthReport = new QBirthDeathReport("birthReport");
        QBirthDeathReport deathReport = new QBirthDeathReport("deathReport");
        QHouseholdComposition householdComposition = QHouseholdComposition.householdComposition;

        JPQLQuery query = from(resident);
        query.leftJoin(birthReport)
                .on(resident.serialNumber.eq(birthReport.pk.residentSerialNumber), birthReport.pk.typeCode.eq("출생"));
        query.leftJoin(deathReport)
                .on(resident.serialNumber.eq(deathReport.pk.residentSerialNumber), deathReport.pk.typeCode.eq("사망"));
        query.innerJoin(householdComposition)
                .on(resident.serialNumber.eq(householdComposition.pk.residentSerialNumber));
        query.where(householdComposition.pk.householdSerialNumber.eq(householdSerialNumber));
        query.select(
                Projections.bean(ResidentDto.class,
                        resident.serialNumber,
                        resident.name,
                        resident.genderCode,
                        resident.birthDate,
                        birthReport.pk.typeCode.as("birthCode"),
                        deathReport.pk.typeCode.as("deathCode")
                )
        );

        long totalCount = query.fetchCount();
        List<ResidentDto> results = getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<>(results, pageable, totalCount);
    }
}
