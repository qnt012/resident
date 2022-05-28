package com.nhnacademy.resident.repository.custom;

import com.nhnacademy.resident.domain.FamilyCompositionDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface FamilyRelationshipRepositoryCustom {
    List<FamilyCompositionDto> findFamilyCompositions(Long serialNumber);
}
