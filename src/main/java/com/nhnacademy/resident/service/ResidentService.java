package com.nhnacademy.resident.service;

import com.nhnacademy.resident.domain.dto.ResidentDto;
import com.nhnacademy.resident.domain.request.ResidentCreateRequest;
import com.nhnacademy.resident.domain.request.ResidentModifyRequest;
import com.nhnacademy.resident.entity.Resident;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResidentService {
    Page<ResidentDto> getResidents(Pageable pageable);
    Resident createResident(ResidentCreateRequest request);
    Resident modifyResident(Long serialNumber, ResidentModifyRequest request);
    void removeResident(Long serialNumber);
}
