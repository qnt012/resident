package com.nhnacademy.resident.repository.custom;

import com.nhnacademy.resident.domain.dto.ResidentDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ResidentRepositoryCustom {
    List<ResidentDto> findResidents();
}
