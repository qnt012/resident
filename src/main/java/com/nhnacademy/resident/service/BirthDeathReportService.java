package com.nhnacademy.resident.service;

import com.nhnacademy.resident.domain.request.BirthDeathReportCreateRequest;
import com.nhnacademy.resident.domain.request.BirthDeathReportModifyRequest;
import com.nhnacademy.resident.entity.BirthDeathReport;

public interface BirthDeathReportService {
    BirthDeathReport getBirthReport(Long serialNumber);
    BirthDeathReport getDeathReport(Long serialNumber);

    BirthDeathReport createBirthDeathReport(Long serialNumber, BirthDeathReportCreateRequest request);
    BirthDeathReport modifyBirthDeathReport(Long serialNumber, Long targetSerialNumber, BirthDeathReportModifyRequest request);
    BirthDeathReport removeBirthDeathReport(Long serialNumber, Long targetSerialNumber, String typeCode);
}
