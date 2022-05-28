package com.nhnacademy.resident.controller;

import com.nhnacademy.resident.service.FamilyRelationshipCertificateService;
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

    public ResidentListController(ResidentService residentService, FamilyRelationshipCertificateService familyRelationshipCertificateService) {
        this.residentService = residentService;
        this.familyRelationshipCertificateService = familyRelationshipCertificateService;
    }

    @GetMapping
    public String getResidents(ModelMap modelMap) {
        modelMap.put("residents", residentService.getResidents());
        return "residentList";
    }

    @GetMapping("{serialNumber}/familyRelationshipCertificate")
    public String getfamilyRelationshipCertificate(@PathVariable Long serialNumber,
                                                   ModelMap modelMap) {
        modelMap.put("top", familyRelationshipCertificateService.getFamilyRelationshipCertificate(serialNumber).get(0));
        return "familyRelationshipCertificate";
    }
}
