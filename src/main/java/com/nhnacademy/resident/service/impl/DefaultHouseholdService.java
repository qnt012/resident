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

import java.util.Optional;

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
        Optional<Resident> resident = residentRepository.findById(request.getResidentSerialNumber());
        if (resident.isEmpty()) throw new ResidentNotFoundException();

        Household household = new Household(request.getSerialNumber(), resident.get(), request.getCompositionDate(), request.getCompositionReasonCode(), request.getCurrentAddress());
        householdRepository.save(household);
        householdCompositionRepository.save(new HouseholdComposition(new HouseholdComposition.Pk(request.getSerialNumber(), request.getResidentSerialNumber()),
                household, resident.get(), request.getCompositionDate(), "본인", request.getCompositionReasonCode()));
        return household;
    }

    @Override
    public Household removeHousehold(Long householdSerialNumber) {
        Optional<Household> household = householdRepository.findById(householdSerialNumber);
        if (household.isEmpty()) throw new HouseholdNotFoundException();

        householdRepository.deleteById(householdSerialNumber);
        householdCompositionRepository.deleteAllByHousehold(household.get());
        return household.get();
    }
}
