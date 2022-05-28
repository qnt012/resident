package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.entity.BirthDeathReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthDeathReportRepository extends JpaRepository<BirthDeathReport, BirthDeathReport.Pk> {
}
