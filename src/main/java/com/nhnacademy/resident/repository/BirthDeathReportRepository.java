package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.entity.BirthDeathReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BirthDeathReportRepository extends JpaRepository<BirthDeathReport, BirthDeathReport.Pk> {
    @Transactional
    @Modifying
    @Query("update BirthDeathReport set reportDate = :#{#birthDeathReport.reportDate}, birthReportQualifyCode = :#{#birthDeathReport.birthReportQualifyCode}," +
            " deathReportQualifyCode = :#{#birthDeathReport.deathReportQualifyCode},email = :#{#birthDeathReport.email},phone = :#{#birthDeathReport.phone} " +
            " where pk.residentSerialNumber = :#{#birthDeathReport.pk.residentSerialNumber} and pk.typeCode = :#{#birthDeathReport.pk.typeCode}")
    void updateBirthDeathReport(@Param("birthDeathReport") BirthDeathReport birthDeathReport );
}
