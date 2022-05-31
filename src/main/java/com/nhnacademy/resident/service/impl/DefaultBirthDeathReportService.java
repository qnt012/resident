package com.nhnacademy.resident.service.impl;

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
}
