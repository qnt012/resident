package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.entity.HouseholdComposition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdCompositionRepository extends JpaRepository<HouseholdComposition, HouseholdComposition.Pk> {
}
