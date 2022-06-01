package com.nhnacademy.resident.controller;

import com.nhnacademy.resident.domain.request.*;
import com.nhnacademy.resident.entity.BirthDeathReport;
import com.nhnacademy.resident.entity.FamilyRelationship;
import com.nhnacademy.resident.entity.Resident;
import com.nhnacademy.resident.service.BirthDeathReportService;
import com.nhnacademy.resident.service.FamilyRelationshipService;
import com.nhnacademy.resident.service.ResidentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residents")
public class ResidentController {
    private final ResidentService residentService;
    private final FamilyRelationshipService familyRelationshipService;
    private final BirthDeathReportService birthDeathReportService;

    public ResidentController(ResidentService residentService, FamilyRelationshipService familyRelationshipService, BirthDeathReportService birthDeathReportService) {
        this.residentService = residentService;
        this.familyRelationshipService = familyRelationshipService;
        this.birthDeathReportService = birthDeathReportService;
    }

    @PostMapping
    public ResponseEntity<Resident> postResidents(@RequestBody ResidentCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(residentService.createResident(request));
    }

    @PutMapping("{serialNumber}")
    public ResponseEntity<Resident> putResident(@PathVariable Long serialNumber,
                                                @RequestBody ResidentModifyRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(residentService.modifyResident(serialNumber, request));
    }

    @PostMapping("/{serialNumber}/relationship")
    public ResponseEntity<FamilyRelationship> postRelationship(@PathVariable Long serialNumber,
                                                               @RequestBody RelationshipCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(familyRelationshipService.createFamilyRelationship(serialNumber, request));
    }

    @PutMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public ResponseEntity<FamilyRelationship> putRelationship(@PathVariable Long serialNumber,
                                                              @PathVariable Long familySerialNumber,
                                                              @RequestBody RelationshipModifyRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(familyRelationshipService.modifyFamilyRelationship(serialNumber, familySerialNumber, request));
    }

    @DeleteMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public ResponseEntity<FamilyRelationship> deleteRelationship(@PathVariable Long serialNumber,
                                                              @PathVariable Long familySerialNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(familyRelationshipService.removeFamilyRelationship(serialNumber, familySerialNumber));
    }

    @PostMapping("{serialNumber}/birth")
    public ResponseEntity<BirthDeathReport> postBirth(@PathVariable Long serialNumber,
                                                      @RequestBody BirthDeathReportCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(birthDeathReportService.createBirthDeathReport(serialNumber, request));

    }

    @PutMapping("{serialNumber}/birth/{targetSerialNumber}")
    public ResponseEntity<BirthDeathReport> putBirth(@PathVariable Long serialNumber,
                                                     @PathVariable Long targetSerialNumber,
                                                     @RequestBody BirthDeathReportModifyRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(birthDeathReportService.modifyBirthDeathReport(serialNumber, targetSerialNumber, request));
    }

    @DeleteMapping("{serialNumber}/birth/{targetSerialNumber}")
    public ResponseEntity<BirthDeathReport> deleteBirth(@PathVariable Long serialNumber,
                                                        @PathVariable Long targetSerialNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(birthDeathReportService.removeBirthDeathReport(serialNumber, targetSerialNumber, "출생"));
    }

    @PostMapping("{serialNumber}/death")
    public ResponseEntity<BirthDeathReport> postDeath(@PathVariable Long serialNumber,
                                                      @RequestBody BirthDeathReportCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(birthDeathReportService.createBirthDeathReport(serialNumber, request));

    }

    @PutMapping("{serialNumber}/death/{targetSerialNumber}")
    public ResponseEntity<BirthDeathReport> putDeath(@PathVariable Long serialNumber,
                                                     @PathVariable Long targetSerialNumber,
                                                     @RequestBody BirthDeathReportModifyRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(birthDeathReportService.modifyBirthDeathReport(serialNumber, targetSerialNumber, request));
    }

    @DeleteMapping("{serialNumber}/death/{targetSerialNumber}")
    public ResponseEntity<BirthDeathReport> deleteDeath(@PathVariable Long serialNumber,
                                                        @PathVariable Long targetSerialNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(birthDeathReportService.removeBirthDeathReport(serialNumber, targetSerialNumber, "사망"));
    }

}