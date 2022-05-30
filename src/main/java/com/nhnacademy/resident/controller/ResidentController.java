package com.nhnacademy.resident.controller;

import com.nhnacademy.resident.domain.request.RelationshipCreateRequest;
import com.nhnacademy.resident.domain.request.RelationshipModifyRequest;
import com.nhnacademy.resident.domain.request.ResidentCreateRequest;
import com.nhnacademy.resident.domain.request.ResidentModifyRequest;
import com.nhnacademy.resident.entity.FamilyRelationship;
import com.nhnacademy.resident.entity.Resident;
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

    public ResidentController(ResidentService residentService, FamilyRelationshipService familyRelationshipService) {
        this.residentService = residentService;
        this.familyRelationshipService = familyRelationshipService;
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
        return ResponseEntity.status(HttpStatus.CREATED)
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
}