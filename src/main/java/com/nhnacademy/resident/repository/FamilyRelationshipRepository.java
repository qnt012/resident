package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.entity.FamilyRelationship;
import com.nhnacademy.resident.repository.custom.FamilyRelationshipRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk>, FamilyRelationshipRepositoryCustom {
    @Transactional
    @Modifying
    @Query("update FamilyRelationship set relationshipCode = :relationship where baseResident.serialNumber = :serialNumber and familyResident.serialNumber = :familySerialNumber")
    void updateRelationship(@Param("serialNumber") Long serialNumber, @Param("familySerialNumber") Long familySerialNumber, @Param("relationship") String relationship);
}
