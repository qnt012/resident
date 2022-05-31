package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.entity.Resident;
import com.nhnacademy.resident.repository.custom.ResidentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ResidentRepository extends JpaRepository<Resident, Long>, ResidentRepositoryCustom {
    @Transactional
    @Modifying
    @Query("update Resident set name = :#{#resident.name}, registrationNumber = :#{#resident.registrationNumber}, genderCode = :#{#resident.genderCode}," +
            " birthDate = :#{#resident.birthDate}, birthPlaceCode = :#{#resident.birthPlaceCode}, registrationBaseAddress = :#{#resident.registrationBaseAddress}," +
            " deathDate = :#{#resident.deathDate}, deathPlaceCode = :#{#resident.deathPlaceCode}," +
            " deathPlaceAddress = :#{#resident.deathPlaceAddress} where serialNumber = :#{#resident.serialNumber}")
    void updateResident(@Param("resident") Resident resident);
}
