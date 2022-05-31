package com.nhnacademy.resident.repository.impl;

import com.nhnacademy.resident.domain.dto.ResidentDto;
import com.nhnacademy.resident.entity.QBirthDeathReport;
import com.nhnacademy.resident.entity.QResident;
import com.nhnacademy.resident.repository.custom.ResidentRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ResidentRepositoryImpl extends QuerydslRepositorySupport implements ResidentRepositoryCustom {
    public ResidentRepositoryImpl() {
        super(ResidentDto.class);
    }

    @Override
    public List<ResidentDto> findResidents() {
        QResident resident = QResident.resident;
        QBirthDeathReport birthReport = new QBirthDeathReport("birthReport");
        QBirthDeathReport deathReport = new QBirthDeathReport("deathReport");

        JPQLQuery query = from(resident);
        query.leftJoin(birthReport)
                .on(resident.serialNumber.eq(birthReport.pk.residentSerialNumber), birthReport.pk.typeCode.eq("출생"));
        query.leftJoin(deathReport)
                .on(resident.serialNumber.eq(deathReport.pk.residentSerialNumber), deathReport.pk.typeCode.eq("사망"));
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

        return query.fetch();
    }
}
