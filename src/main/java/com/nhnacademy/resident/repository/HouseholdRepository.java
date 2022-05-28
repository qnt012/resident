package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household, Long> {
}
