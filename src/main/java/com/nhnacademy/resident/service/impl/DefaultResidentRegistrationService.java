package com.nhnacademy.resident.service.impl;

import com.nhnacademy.resident.domain.dto.HouseholdDto;
import com.nhnacademy.resident.domain.dto.ResidentRegistrationDto;
import com.nhnacademy.resident.entity.CertificateIssue;
import com.nhnacademy.resident.entity.Resident;
import com.nhnacademy.resident.exception.HouseholdNotFoundException;
import com.nhnacademy.resident.repository.CertificateIssueRepository;
import com.nhnacademy.resident.repository.HouseholdRepository;
import com.nhnacademy.resident.repository.ResidentRepository;
import com.nhnacademy.resident.service.ResidentRegistrationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
public class DefaultResidentRegistrationService implements ResidentRegistrationService {
    private final ResidentRepository residentRepository;
    private final HouseholdRepository householdRepository;
    private final CertificateIssueRepository certificateIssueRepository;
    private final Random random = new Random();

    public DefaultResidentRegistrationService(ResidentRepository residentRepository, HouseholdRepository householdRepository, CertificateIssueRepository certificateIssueRepository) {
        this.residentRepository = residentRepository;
        this.householdRepository = householdRepository;
        this.certificateIssueRepository = certificateIssueRepository;
    }

    @Override
    public ResidentRegistrationDto getResidentRegistrationDto(Long serialNumber) {
        HouseholdDto householdDto = householdRepository.findResidentRegistrationDto(serialNumber);
        if (Objects.isNull(householdDto)) throw new HouseholdNotFoundException();
        Optional<Resident> resident = residentRepository.findById(serialNumber);
        CertificateIssue certificateIssue = new CertificateIssue(9876543200000000L + random.nextInt(100000000), resident.get(),"주민등록등본", LocalDate.now());
        certificateIssueRepository.save(certificateIssue);
        return new ResidentRegistrationDto(certificateIssue.getIssueDate(), certificateIssue.getConfirmationNumber(), householdDto.getName(),
                householdDto.getCompositionReasonCode(), householdDto.getCompositionDate());
    }
}