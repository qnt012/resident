package com.nhnacademy.resident.service.impl;

import com.nhnacademy.resident.domain.request.HouseholdCreateRequest;
import com.nhnacademy.resident.entity.Household;
import com.nhnacademy.resident.entity.HouseholdComposition;
import com.nhnacademy.resident.entity.Resident;
import com.nhnacademy.resident.exception.HouseholdNotFoundException;
import com.nhnacademy.resident.exception.ResidentNotFoundException;
import com.nhnacademy.resident.repository.HouseholdCompositionRepository;
import com.nhnacademy.resident.repository.HouseholdRepository;
import com.nhnacademy.resident.repository.ResidentRepository;
import com.nhnacademy.resident.service.HouseholdService;
import org.springframework.stereotype.Service;

@Service
public class DefaultHouseholdService implements HouseholdService {
    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;
    private final HouseholdCompositionRepository householdCompositionRepository;

    public DefaultHouseholdService(HouseholdRepository householdRepository, ResidentRepository residentRepository,
                                   HouseholdCompositionRepository householdCompositionRepository) {
        this.householdRepository = householdRepository;
        this.residentRepository = residentRepository;
        this.householdCompositionRepository = householdCompositionRepository;
    }

    @Override
    public Household createHousehold(HouseholdCreateRequest request) {
        Resident resident = residentRepository.findById(request.getResidentSerialNumber()).orElseThrow(ResidentNotFoundException::new);

        Household household = new Household(request.getSerialNumber(), resident, request.getCompositionDate(), request.getCompositionReasonCode(), request.getCurrentAddress());
        householdRepository.save(household);
        householdCompositionRepository.save(new HouseholdComposition(new HouseholdComposition.Pk(request.getSerialNumber(), request.getResidentSerialNumber()),
                household, resident, request.getCompositionDate(), "본인", request.getCompositionReasonCode()));
        return household;
    }

    @Override
    public Household removeHousehold(Long householdSerialNumber) {
        Household household = householdRepository.findById(householdSerialNumber).orElseThrow(HouseholdNotFoundException::new);
        householdRepository.deleteById(householdSerialNumber);
        return household;
    }
}
