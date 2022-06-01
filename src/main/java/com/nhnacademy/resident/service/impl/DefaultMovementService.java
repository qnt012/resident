package com.nhnacademy.resident.service.impl;

import com.nhnacademy.resident.domain.request.MovementAddressRequest;
import com.nhnacademy.resident.entity.Household;
import com.nhnacademy.resident.entity.MovementAddress;
import com.nhnacademy.resident.exception.HouseholdNotFoundException;
import com.nhnacademy.resident.exception.MovementAddressNotFoundException;
import com.nhnacademy.resident.repository.HouseholdRepository;
import com.nhnacademy.resident.repository.MovementAddressRepository;
import com.nhnacademy.resident.service.MovementAddressService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DefaultMovementService implements MovementAddressService {
    private final MovementAddressRepository movementAddressRepository;
    private final HouseholdRepository householdRepository;

    public DefaultMovementService(MovementAddressRepository movementAddressRepository, HouseholdRepository householdRepository) {
        this.movementAddressRepository = movementAddressRepository;
        this.householdRepository = householdRepository;
    }

    @Override
    public MovementAddress createMovementAddress(Long householdSerialNumber, MovementAddressRequest request) {
        MovementAddress.Pk pk = new MovementAddress.Pk(householdSerialNumber, LocalDate.now());
        Household household = householdRepository.findById(householdSerialNumber).orElseThrow(HouseholdNotFoundException::new);
        MovementAddress movementAddress = new MovementAddress(pk, household, request.getAddress(), request.getLastAddressYn());

        movementAddressRepository.save(movementAddress);

        return movementAddress;
    }

    @Override
    public MovementAddress modifyMovementAddress(Long householdSerialNumber, LocalDate reportDate, MovementAddressRequest request) {
        MovementAddress movementAddress =
                movementAddressRepository.findById(new MovementAddress.Pk(householdSerialNumber, reportDate)).orElseThrow(MovementAddressNotFoundException::new);

        movementAddress.setAddress(request.getAddress());
        movementAddress.setLastAddressYn(request.getLastAddressYn());

        movementAddressRepository.updateBirthDeathReport(movementAddress);
        return movementAddress;
    }

    @Override
    public MovementAddress removeMovementAddress(Long householdSerialNumber, LocalDate reportDate) {
        MovementAddress.Pk pk = new MovementAddress.Pk(householdSerialNumber, reportDate);
        MovementAddress movementAddress = movementAddressRepository.findById(pk).orElseThrow(MovementAddressNotFoundException::new);

        movementAddressRepository.deleteById(pk);

        return movementAddress;
    }
}
