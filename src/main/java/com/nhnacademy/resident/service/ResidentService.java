package com.nhnacademy.resident.service;

import com.nhnacademy.resident.domain.request.ResidentCreateRequest;
import com.nhnacademy.resident.domain.request.ResidentModifyRequest;
import com.nhnacademy.resident.entity.Resident;

import java.util.List;

public interface ResidentService {
    List<Resident> getResidents();
    Resident createResident(ResidentCreateRequest request);
    Resident modifyResident(Long serialNumber, ResidentModifyRequest request);
}
