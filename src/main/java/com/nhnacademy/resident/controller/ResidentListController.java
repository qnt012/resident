package com.nhnacademy.resident.controller;

import com.nhnacademy.resident.domain.dto.ResidentRegistrationDto;
import com.nhnacademy.resident.service.CertificateIssueService;
import com.nhnacademy.resident.service.FamilyRelationshipCertificateService;
import com.nhnacademy.resident.service.ResidentRegistrationService;
import com.nhnacademy.resident.service.ResidentService;
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

    public ResidentListController(ResidentService residentService, FamilyRelationshipCertificateService familyRelationshipCertificateService,
                                  CertificateIssueService certificateIssueService, ResidentRegistrationService residentRegistrationService) {
        this.residentService = residentService;
        this.familyRelationshipCertificateService = familyRelationshipCertificateService;
        this.certificateIssueService = certificateIssueService;
        this.residentRegistrationService = residentRegistrationService;
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
        modelMap.put("top", residentRegistrationDto);
        modelMap.put("movementAddresses", residentRegistrationService.getMovementAddresses(residentRegistrationDto.getHouseholdSerialNumber()));
        return "residentRegistration";
    }

    @GetMapping("{serialNumber}/certificateIssue")
    public String getCertificateIssue(@PathVariable Long serialNumber,
                                                   ModelMap modelMap) {
        modelMap.put("issues", certificateIssueService.getCertificateIssues(serialNumber));
        return "certificationIssueList";
    }
}
