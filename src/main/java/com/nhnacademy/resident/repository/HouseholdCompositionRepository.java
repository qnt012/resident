package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.domain.dto.HouseholdCompositionDto;
import com.nhnacademy.resident.entity.HouseholdComposition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseholdCompositionRepository extends JpaRepository<HouseholdComposition, HouseholdComposition.Pk> {
    List<HouseholdCompositionDto> findAllByPkHouseholdSerialNumber(Long householdSerialNumber);
}
