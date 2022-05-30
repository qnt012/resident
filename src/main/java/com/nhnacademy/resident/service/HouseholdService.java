package com.nhnacademy.resident.service;

import com.nhnacademy.resident.domain.request.HouseholdCreateRequest;
import com.nhnacademy.resident.entity.Household;

public interface HouseholdService {
    Household createHousehold(HouseholdCreateRequest request);

    Household removeHousehold(Long householdSerialNumber);
}
