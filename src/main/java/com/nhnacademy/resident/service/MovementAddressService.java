package com.nhnacademy.resident.service;

import com.nhnacademy.resident.domain.request.MovementAddressRequest;
import com.nhnacademy.resident.entity.MovementAddress;

import java.time.LocalDate;

public interface MovementAddressService {
    MovementAddress createMovementAddress(Long householdSerialNumber, MovementAddressRequest request);

    MovementAddress modifyMovementAddress(Long householdSerialNumber, LocalDate reportDate, MovementAddressRequest request);

    MovementAddress removeMovementAddress(Long householdSerialNumber, LocalDate reportDate);
}
