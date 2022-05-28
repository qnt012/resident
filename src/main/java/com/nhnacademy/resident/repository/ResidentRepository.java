package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, Long> {
}
