package com.nhnacademy.resident.service;

import com.nhnacademy.resident.domain.dto.ResidentRegistrationDto;

public interface ResidentRegistrationService {
    ResidentRegistrationDto getResidentRegistrationDto(Long serialNumber);
}
