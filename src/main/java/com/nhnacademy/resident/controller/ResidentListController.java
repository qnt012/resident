package com.nhnacademy.resident.controller;

import com.nhnacademy.resident.domain.dto.CertificateIssueDto;
import com.nhnacademy.resident.domain.dto.ResidentDto;
import com.nhnacademy.resident.domain.dto.ResidentRegistrationDto;
import com.nhnacademy.resident.entity.BirthDeathReport;
import com.nhnacademy.resident.exception.BirthDeathReportNotFoundException;
import com.nhnacademy.resident.service.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public String getResidents(ModelMap modelMap,
                               Pageable pageable) {
        if (pageable.getPageSize() != 5) pageable = PageRequest.of(0, 5);
        Page<ResidentDto> residents = residentService.getResidents(pageable);
        modelMap.put("residents", residents);
        int totalPages = residents.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages-1)
                .boxed()
                .collect(Collectors.toList());
            modelMap.put("pageNumbers", pageNumbers);
        }
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
                                      ModelMap modelMap,
                                      Pageable pageable) {
        if (pageable.getPageSize() != 5) pageable = PageRequest.of(0, 5);
        Page<CertificateIssueDto> issues = certificateIssueService.getCertificateIssues(serialNumber, pageable);
        modelMap.put("issues", issues);
        int totalPages = issues.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages-1)
                .boxed()
                .collect(Collectors.toList());
            modelMap.put("pageNumbers", pageNumbers);
        }
        modelMap.put("serialNumber", serialNumber);
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

    @GetMapping("{serialNumber}/delete")
    public String getResidentDelete(@PathVariable Long serialNumber) {
        residentService.removeResident(serialNumber);
        return "redirect:/residents";
    }
}
