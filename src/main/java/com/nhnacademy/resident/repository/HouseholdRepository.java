package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.entity.Household;
import com.nhnacademy.resident.repository.custom.HouseholdRepositoryCustom;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household, Long>, HouseholdRepositoryCustom {
    Optional<Household> findByResidentSerialNumber(Long serialNumber);
}
