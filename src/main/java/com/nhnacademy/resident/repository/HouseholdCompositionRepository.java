package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.domain.dto.HouseholdCompositionDto;
import com.nhnacademy.resident.entity.Household;
import com.nhnacademy.resident.entity.HouseholdComposition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HouseholdCompositionRepository extends JpaRepository<HouseholdComposition, HouseholdComposition.Pk> {
    List<HouseholdCompositionDto> findAllByPkHouseholdSerialNumber(Long householdSerialNumber);
    Optional<HouseholdComposition> findByPkResidentSerialNumber(Long serialNumber);
    void deleteAllByHousehold(Household household);
}
