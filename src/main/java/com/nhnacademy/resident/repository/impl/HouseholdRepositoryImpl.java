package com.nhnacademy.resident.repository.impl;

import com.nhnacademy.resident.domain.dto.HouseholdDto;
import com.nhnacademy.resident.entity.Household;
import com.nhnacademy.resident.entity.QHousehold;
import com.nhnacademy.resident.entity.QHouseholdComposition;
import com.nhnacademy.resident.repository.custom.HouseholdRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


public class HouseholdRepositoryImpl extends QuerydslRepositorySupport implements HouseholdRepositoryCustom {
    public HouseholdRepositoryImpl() {
        super(Household.class);
    }

    @Override
    public HouseholdDto findResidentRegistrationDto(Long serialNumber) {
        QHousehold household = QHousehold.household;
        QHouseholdComposition householdComposition = QHouseholdComposition.householdComposition;

        JPQLQuery query = from(household);
        query.innerJoin(householdComposition).on(household.serialNumber.eq(householdComposition.household.serialNumber));
        query.innerJoin(household.resident);
        query.where(householdComposition.resident.serialNumber.eq(serialNumber));

        query.select(
                Projections.bean(HouseholdDto.class,
                        household.resident.name,
                        household.compositionReasonCode,
                        household.compositionDate
                )
        );

        return (HouseholdDto) query.fetchOne();
    }
}