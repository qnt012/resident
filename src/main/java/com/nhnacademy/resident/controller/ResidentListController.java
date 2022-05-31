package com.nhnacademy.resident.controller;

import com.nhnacademy.resident.domain.dto.ResidentRegistrationDto;
import com.nhnacademy.resident.entity.BirthDeathReport;
import com.nhnacademy.resident.exception.BirthDeathReportNotFoundException;
import com.nhnacademy.resident.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/residents")
public class ResidentListController {
    private final ResidentService residentService;
    private final FamilyRelationshipCertificateService familyRelationshipCertificateService;
    private final CertificateIssueService certificateIssueService;
    private final ResidentRegistrationService residentRegistrationService;
    private final BirthDeathReportService birthDeathReportRepository;

    public ResidentListController(ResidentService residentService, FamilyRelationshipCertificateService familyRelationshipCertificateService,
                                  CertificateIssueService certificateIssueService, ResidentRegistrationService residentRegistrationService,
                                  BirthDeathReportService birthDeathReportRepository) {
        this.residentService = residentService;
        this.familyRelationshipCertificateService = familyRelationshipCertificateService;
        this.certificateIssueService = certificateIssueService;
        this.residentRegistrationService = residentRegistrationService;
        this.birthDeathReportRepository = birthDeathReportRepository;
    }

    @GetMapping
    public String getResidents(ModelMap modelMap) {
        modelMap.put("residents", residentService.getResidents());
        return "residentList";
    }

    @GetMapping("{serialNumber}/familyRelationshipCertificate")
    public String getFamilyRelationshipCertificate(@PathVariable Long serialNumber,
                                                   ModelMap modelMap) {
        modelMap.put("top", familyRelationshipCertificateService.getFamilyRelationshipCertificate(serialNumber));
        modelMap.put("compositions", familyRelationshipCertificateService.getFamilyCompositions(serialNumber));
        return "familyRelationshipCertificate";
    }

    @GetMapping("{serialNumber}/residentRegistration")
    public String getResidentRegistration(@PathVariable Long serialNumber,
                                                   ModelMap modelMap) {
        ResidentRegistrationDto residentRegistrationDto = residentRegistrationService.getResidentRegistrationDto(serialNumber);
        Long householdSerialNumber = residentRegistrationDto.getHouseholdSerialNumber();
        modelMap.put("top", residentRegistrationDto);
        modelMap.put("movementAddresses", residentRegistrationService.getMovementAddresses(householdSerialNumber));
        modelMap.put("compositions", residentRegistrationService.getHouseholdComposition(householdSerialNumber));
        return "residentRegistration";
    }

    @GetMapping("{serialNumber}/certificateIssue")
    public String getCertificateIssue(@PathVariable Long serialNumber,
                                                   ModelMap modelMap) {
        modelMap.put("issues", certificateIssueService.getCertificateIssues(serialNumber));
        return "certificationIssueList";
    }

    @GetMapping("{serialNumber}/birthReport")
    public String getBirthReport(@PathVariable Long serialNumber,
                           ModelMap modelMap) {
        modelMap.put("birthReport", birthDeathReportRepository.getBirthReport(serialNumber));
        return "birthReport";
    }

    @GetMapping("{serialNumber}/deathReport")
    public String getDeathReport(@PathVariable Long serialNumber,
                                 ModelMap modelMap) {
        modelMap.put("deathReport", birthDeathReportRepository.getDeathReport(serialNumber));
        return "deathReport";
    }
}
