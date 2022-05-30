package com.nhnacademy.resident.controller;

import com.nhnacademy.resident.domain.request.HouseholdCreateRequest;
import com.nhnacademy.resident.entity.Household;
import com.nhnacademy.resident.service.HouseholdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/household")
public class HouseholdController {
    private final HouseholdService householdService;

    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @PostMapping
    public ResponseEntity<Household> postHousehold(@RequestBody HouseholdCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(householdService.createHousehold(request));
    }

    @DeleteMapping("/{householdSerialNumber}")
    public ResponseEntity<Household> deleteHousehold(@PathVariable Long householdSerialNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(householdService.removeHousehold(householdSerialNumber));
    }
}
