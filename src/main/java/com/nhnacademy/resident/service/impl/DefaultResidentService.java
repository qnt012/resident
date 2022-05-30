package com.nhnacademy.resident.service.impl;

import com.nhnacademy.resident.domain.request.ResidentCreateRequest;
import com.nhnacademy.resident.domain.request.ResidentModifyRequest;
import com.nhnacademy.resident.entity.Resident;
import com.nhnacademy.resident.repository.ResidentRepository;
import com.nhnacademy.resident.service.ResidentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultResidentService implements ResidentService {
    private final ResidentRepository residentRepository;

    public DefaultResidentService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    @Override
    public List<Resident> getResidents() {
        return residentRepository.findAll();
    }

    @Override
    public Resident createResident(ResidentCreateRequest request) {
        Resident resident = new Resident(request.getSerialNumber(), request.getName(), request.getRegistrationNumber(), request.getGenderCode(),
                request.getBirthDate(), request.getBirthPlaceCode(), request.getRegistrationBaseAddress(), null, null, null);
        residentRepository.save(resident);
        return resident;
    }

    @Override
    public Resident modifyResident(Long serialNumber, ResidentModifyRequest request) {
        Resident resident = new Resident(serialNumber, request.getName(), request.getRegistrationNumber(), request.getGenderCode(),
                request.getBirthDate(), request.getBirthPlaceCode(), request.getRegistrationBaseAddress(), request.getDeathDate(),
                request.getDeathPlaceCode(), request.getDeathPlaceAddress());
        residentRepository.updateResident(resident);
        return resident;
    }
}
