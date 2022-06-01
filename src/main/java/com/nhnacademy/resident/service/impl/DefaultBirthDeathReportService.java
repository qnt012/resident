package com.nhnacademy.resident.service.impl;

import com.nhnacademy.resident.domain.request.BirthDeathReportCreateRequest;
import com.nhnacademy.resident.domain.request.BirthDeathReportModifyRequest;
import com.nhnacademy.resident.domain.request.RelationshipCreateRequest;
import com.nhnacademy.resident.entity.BirthDeathReport;
import com.nhnacademy.resident.entity.CertificateIssue;
import com.nhnacademy.resident.entity.Resident;
import com.nhnacademy.resident.exception.BirthDeathReportNotFoundException;
import com.nhnacademy.resident.exception.ResidentNotFoundException;
import com.nhnacademy.resident.repository.BirthDeathReportRepository;
import com.nhnacademy.resident.repository.CertificateIssueRepository;
import com.nhnacademy.resident.repository.ResidentRepository;
import com.nhnacademy.resident.service.BirthDeathReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class DefaultBirthDeathReportService implements BirthDeathReportService {
    private final BirthDeathReportRepository birthDeathReportRepository;
    private final ResidentRepository residentRepository;
    private final CertificateIssueRepository certificateIssueRepository;
    private final Random random = new Random();

    public DefaultBirthDeathReportService(BirthDeathReportRepository birthDeathReportRepository, ResidentRepository residentRepository, CertificateIssueRepository certificateIssueRepository) {
        this.birthDeathReportRepository = birthDeathReportRepository;
        this.residentRepository = residentRepository;
        this.certificateIssueRepository = certificateIssueRepository;
    }

    @Override
    public BirthDeathReport getBirthReport(Long serialNumber) {
        Resident resident = residentRepository.findById(serialNumber).orElseThrow(ResidentNotFoundException::new);

        CertificateIssue certificateIssue = new CertificateIssue(1111111100000000L + random.nextInt(100000000), resident,"출생신고서", LocalDate.now());
        certificateIssueRepository.save(certificateIssue);

        return birthDeathReportRepository.findById(new BirthDeathReport.Pk(serialNumber, "출생"))
                .orElseThrow(BirthDeathReportNotFoundException::new);
    }

    @Override
    public BirthDeathReport getDeathReport(Long serialNumber) {
        Resident resident = residentRepository.findById(serialNumber).orElseThrow(ResidentNotFoundException::new);

        CertificateIssue certificateIssue = new CertificateIssue(9999999900000000L + random.nextInt(100000000), resident,"사망신고서", LocalDate.now());
        certificateIssueRepository.save(certificateIssue);

        return birthDeathReportRepository.findById(new BirthDeathReport.Pk(serialNumber, "사망"))
                .orElseThrow(BirthDeathReportNotFoundException::new);
    }

    @Override
    public BirthDeathReport createBirthDeathReport(Long serialNumber, BirthDeathReportCreateRequest request) {
        BirthDeathReport.Pk pk = new BirthDeathReport.Pk(request.getResidentSerialNumber(), request.getTypeCode());

        Resident resident = residentRepository.findById(request.getResidentSerialNumber()).orElseThrow(ResidentNotFoundException::new);
        Resident reportResident = residentRepository.findById(serialNumber).orElseThrow(ResidentNotFoundException::new);

        BirthDeathReport birthDeathReport = new BirthDeathReport(pk, resident, reportResident, request.getReportDate(), request.getBirthReportQualifyCode(),
                request.getDeathReportQualifyCode(), request.getEmail(), request.getPhone());

        birthDeathReportRepository.save(birthDeathReport);

        return birthDeathReport;
    }

    @Override
    public BirthDeathReport modifyBirthDeathReport(Long serialNumber, Long targetSerialNumber, BirthDeathReportModifyRequest request) {
        BirthDeathReport.Pk pk = new BirthDeathReport.Pk(targetSerialNumber, request.getTypeCode());
        BirthDeathReport birthDeathReport = birthDeathReportRepository.findById(pk).orElseThrow(BirthDeathReportNotFoundException::new);

        birthDeathReport.setReportDate(request.getReportDate());
        birthDeathReport.setBirthReportQualifyCode(request.getBirthReportQualifyCode());
        birthDeathReport.setDeathReportQualifyCode(request.getDeathReportQualifyCode());
        birthDeathReport.setEmail(request.getEmail());
        birthDeathReport.setPhone(request.getPhone());

        birthDeathReportRepository.updateBirthDeathReport(birthDeathReport);

        return birthDeathReport;
    }

    @Override
    public BirthDeathReport removeBirthDeathReport(Long serialNumber, Long targetSerialNumber, String typeCode) {
        BirthDeathReport.Pk pk = new BirthDeathReport.Pk(targetSerialNumber, typeCode);
        BirthDeathReport birthDeathReport = birthDeathReportRepository.findById(pk).orElseThrow(BirthDeathReportNotFoundException::new);
        birthDeathReportRepository.deleteById(pk);

        return birthDeathReport;
    }
}
