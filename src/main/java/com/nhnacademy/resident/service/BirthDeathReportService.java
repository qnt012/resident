package com.nhnacademy.resident.service;

import com.nhnacademy.resident.entity.BirthDeathReport;

public interface BirthDeathReportService {
    BirthDeathReport getBirthReport(Long serialNumber);
    BirthDeathReport getDeathReport(Long serialNumber);
}
