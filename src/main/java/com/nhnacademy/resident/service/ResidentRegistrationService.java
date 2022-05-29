package com.nhnacademy.resident.service;

import com.nhnacademy.resident.domain.dto.HouseholdCompositionDto;
import com.nhnacademy.resident.domain.dto.MovementAddressDto;
import com.nhnacademy.resident.domain.dto.ResidentRegistrationDto;

import java.util.List;

public interface ResidentRegistrationService {
    ResidentRegistrationDto getResidentRegistrationDto(Long serialNumber);
    List<MovementAddressDto> getMovementAddresses(Long householdSerialNumber);
    List<HouseholdCompositionDto> getHouseholdComposition(Long householdSerialNumber);
}
