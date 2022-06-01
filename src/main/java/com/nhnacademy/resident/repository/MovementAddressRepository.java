package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.domain.dto.MovementAddressDto;
import com.nhnacademy.resident.entity.MovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MovementAddressRepository extends JpaRepository<MovementAddress, MovementAddress.Pk> {
    List<MovementAddressDto> findAllByPkHouseholdSerialNumberOrderByPkReportDateDesc(Long householdSerialNumber);

    @Transactional
    @Modifying
    @Query("update MovementAddress set address = :#{#movementAddress.address}, lastAddressYn = :#{#movementAddress.lastAddressYn}" +
            " where pk.householdSerialNumber = :#{#movementAddress.pk.householdSerialNumber} and pk.reportDate = :#{#movementAddress.pk.reportDate}")
    void updateBirthDeathReport(@Param("movementAddress") MovementAddress movementAddress );
}
