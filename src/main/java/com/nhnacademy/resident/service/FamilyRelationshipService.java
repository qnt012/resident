package com.nhnacademy.resident.service;

import com.nhnacademy.resident.domain.request.RelationshipCreateRequest;
import com.nhnacademy.resident.domain.request.RelationshipModifyRequest;
import com.nhnacademy.resident.entity.FamilyRelationship;

public interface FamilyRelationshipService {
    FamilyRelationship createFamilyRelationship(Long serialNumber, RelationshipCreateRequest request);

    FamilyRelationship modifyFamilyRelationship(Long serialNumber, Long familySerialNumber, RelationshipModifyRequest request);

    FamilyRelationship removeFamilyRelationship(Long serialNumber, Long familySerialNumber);
}
