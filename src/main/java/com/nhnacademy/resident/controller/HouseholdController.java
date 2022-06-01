package com.nhnacademy.resident.controller;

import com.nhnacademy.resident.domain.request.HouseholdCreateRequest;
import com.nhnacademy.resident.domain.request.MovementAddressRequest;
import com.nhnacademy.resident.entity.Household;
import com.nhnacademy.resident.entity.MovementAddress;
import com.nhnacademy.resident.service.HouseholdService;
import com.nhnacademy.resident.service.MovementAddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/household")
public class HouseholdController {
    private final HouseholdService householdService;
    private final MovementAddressService movementAddressService;

    public HouseholdController(HouseholdService householdService, MovementAddressService movementAddressService) {
        this.householdService = householdService;
        this.movementAddressService = movementAddressService;
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

    @PostMapping("{householdSerialNumber}/movement")
    public ResponseEntity<MovementAddress> postDeath(@PathVariable Long householdSerialNumber,
                                                     @RequestBody MovementAddressRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(movementAddressService.createMovementAddress(householdSerialNumber, request));

    }

    @PutMapping("{householdSerialNumber}/movement/{reportDate}")
    public ResponseEntity<MovementAddress> putDeath(@PathVariable Long householdSerialNumber,
                                                     @PathVariable String reportDate,
                                                     @RequestBody MovementAddressRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(movementAddressService.modifyMovementAddress(householdSerialNumber, LocalDate.parse(reportDate, DateTimeFormatter.BASIC_ISO_DATE), request));
    }

    @DeleteMapping("{householdSerialNumber}/movement/{reportDate}")
    public ResponseEntity<MovementAddress> deleteDeath(@PathVariable Long householdSerialNumber,
                                                        @PathVariable String reportDate) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(movementAddressService.removeMovementAddress(householdSerialNumber, LocalDate.parse(reportDate, DateTimeFormatter.BASIC_ISO_DATE)));
    }
}
