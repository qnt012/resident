package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.entity.FamilyRelationship;
import com.nhnacademy.resident.repository.custom.FamilyRelationshipRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk>, FamilyRelationshipRepositoryCustom {
}
