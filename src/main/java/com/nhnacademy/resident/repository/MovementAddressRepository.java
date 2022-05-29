package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.domain.dto.MovementAddressDto;
import com.nhnacademy.resident.entity.MovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovementAddressRepository extends JpaRepository<MovementAddress, MovementAddress.Pk> {
    List<MovementAddressDto> findAllByPkHouseholdSerialNumberOrderByPkReportDateDesc(Long householdSerialNumber);
}
