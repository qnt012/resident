package com.nhnacademy.resident.service.impl;

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
}
