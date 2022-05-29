package com.nhnacademy.resident.repository.custom;

import com.nhnacademy.resident.domain.dto.HouseholdDto;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface HouseholdRepositoryCustom {
    HouseholdDto findResidentRegistrationDto(Long serialNumber);
}
