package com.nhnacademy.resident.repository.custom;

import com.nhnacademy.resident.domain.dto.ResidentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ResidentRepositoryCustom {
    Page<ResidentDto> findResidents(Pageable pageable, Long householdSerialNumber);
}
