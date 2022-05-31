package com.nhnacademy.resident.service.impl;

import com.nhnacademy.resident.domain.request.RelationshipCreateRequest;
import com.nhnacademy.resident.domain.request.RelationshipModifyRequest;
import com.nhnacademy.resident.entity.FamilyRelationship;
import com.nhnacademy.resident.entity.Resident;
import com.nhnacademy.resident.exception.FamilyRelationshipNotFoundException;
import com.nhnacademy.resident.exception.ResidentNotFoundException;
import com.nhnacademy.resident.repository.FamilyRelationshipRepository;
import com.nhnacademy.resident.repository.ResidentRepository;
import com.nhnacademy.resident.service.FamilyRelationshipService;
import org.springframework.stereotype.Service;

@Service
public class DefaultFamilyRelationshipService implements FamilyRelationshipService {
    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final ResidentRepository residentRepository;


    public DefaultFamilyRelationshipService(FamilyRelationshipRepository familyRelationshipRepository, ResidentRepository residentRepository) {
        this.familyRelationshipRepository = familyRelationshipRepository;
        this.residentRepository = residentRepository;
    }

    @Override
    public FamilyRelationship createFamilyRelationship(Long serialNumber, RelationshipCreateRequest request) {
        Resident baseResident = residentRepository.findById(serialNumber).orElseThrow(ResidentNotFoundException::new);
        Resident familyResident = residentRepository.findById(request.getFamilySerialNumber()).orElseThrow(ResidentNotFoundException::new);

        FamilyRelationship familyRelationship = new FamilyRelationship(new FamilyRelationship.Pk(serialNumber, request.getFamilySerialNumber()),
                baseResident, familyResident, request.getRelationship());
        familyRelationshipRepository.save(familyRelationship);
        return familyRelationship;
    }

    @Override
    public FamilyRelationship modifyFamilyRelationship(Long serialNumber, Long familySerialNumber, RelationshipModifyRequest request) {
        FamilyRelationship familyRelationship = familyRelationshipRepository.findById(new FamilyRelationship.Pk(serialNumber, familySerialNumber))
                .orElseThrow(FamilyRelationshipNotFoundException::new);

        familyRelationship.setRelationshipCode(request.getRelationship());
        familyRelationshipRepository.updateRelationship(serialNumber, familySerialNumber, request.getRelationship());
        return familyRelationship;
    }

    @Override
    public FamilyRelationship removeFamilyRelationship(Long serialNumber, Long familySerialNumber) {
        FamilyRelationship.Pk pk = new FamilyRelationship.Pk(serialNumber, familySerialNumber);
        FamilyRelationship familyRelationship = familyRelationshipRepository.findById(pk).orElseThrow(FamilyRelationshipNotFoundException::new);

        familyRelationshipRepository.deleteById(pk);
        return familyRelationship;
    }
}
