package com.nhnacademy.resident.service.impl;

import com.nhnacademy.resident.domain.dto.ResidentDto;
import com.nhnacademy.resident.domain.request.ResidentCreateRequest;
import com.nhnacademy.resident.domain.request.ResidentModifyRequest;
import com.nhnacademy.resident.entity.Authority;
import com.nhnacademy.resident.entity.Household;
import com.nhnacademy.resident.entity.HouseholdComposition;
import com.nhnacademy.resident.entity.Resident;
import com.nhnacademy.resident.exception.RemainFamilyException;
import com.nhnacademy.resident.exception.ResidentNotFoundException;
import com.nhnacademy.resident.repository.HouseholdCompositionRepository;
import com.nhnacademy.resident.repository.HouseholdRepository;
import com.nhnacademy.resident.repository.ResidentRepository;
import com.nhnacademy.resident.service.ResidentService;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefaultResidentService implements ResidentService {
    private final ResidentRepository residentRepository;
    private final HouseholdRepository householdRepository;
    private final HouseholdCompositionRepository householdCompositionRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaultResidentService(ResidentRepository residentRepository,
                                  HouseholdRepository householdRepository,
                                  HouseholdCompositionRepository householdCompositionRepository, PasswordEncoder passwordEncoder) {
        this.residentRepository = residentRepository;
        this.householdRepository = householdRepository;
        this.householdCompositionRepository = householdCompositionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<ResidentDto> getResidents(Pageable pageable, String residentId) {
        Resident resident = residentRepository.findByResidentId(residentId).orElseThrow(ResidentNotFoundException::new);
        Optional<HouseholdComposition> householdComposition = householdCompositionRepository.findByPkResidentSerialNumber(resident.getSerialNumber());

        if(householdComposition.isPresent()) {
            return residentRepository.findResidents(pageable, householdComposition.get().getPk().getHouseholdSerialNumber());
        }

        //속한 세대가 없을 경우 본인만 조회
        ResidentDto residentDto = new ResidentDto();
        residentDto.setSerialNumber(resident.getSerialNumber());
        residentDto.setName(resident.getName());
        residentDto.setGenderCode(resident.getGenderCode());
        residentDto.setBirthDate(resident.getBirthDate());
        residentDto.setBirthCode(resident.getBirthPlaceCode());
        residentDto.setDeathCode(resident.getDeathPlaceCode());

        return new PageImpl<>(List.of(residentDto));
    }

    @Override
    public Resident createResident(ResidentCreateRequest request) {
        Resident resident = new Resident(request.getSerialNumber(), request.getName(), request.getRegistrationNumber(), request.getGenderCode(),
                request.getBirthDate(), request.getBirthPlaceCode(), request.getRegistrationBaseAddress(), null, null, null,
                request.getId(), passwordEncoder.encode(request.getPwd()), request.getEmail());

        Authority authority = new Authority();
        authority.setSerialNumber(request.getSerialNumber());
        authority.setResident(resident);
        authority.setAuthority(request.getAuthority());

        resident.setAuthority(authority);
        residentRepository.save(resident);
        return resident;
    }

    @Override
    public Resident modifyResident(Long serialNumber, ResidentModifyRequest request) {
        Resident resident = residentRepository.findById(serialNumber).orElseThrow(ResidentNotFoundException::new);

        resident.setName(request.getName());
        resident.setRegistrationBaseAddress(request.getRegistrationNumber());
        resident.setGenderCode(request.getGenderCode());
        resident.setBirthDate(request.getBirthDate());
        resident.setBirthPlaceCode(request.getBirthPlaceCode());
        resident.setRegistrationBaseAddress(request.getRegistrationBaseAddress());
        resident.setDeathDate(request.getDeathDate());
        resident.setDeathPlaceCode(request.getDeathPlaceCode());
        resident.setDeathPlaceAddress(request.getDeathPlaceAddress());

        residentRepository.updateResident(resident);
        return resident;
    }

    @Override
    public void removeResident(Long serialNumber) {
        Optional<Household> household = householdRepository.findByResidentSerialNumber(serialNumber);
        if (household.isPresent() && householdCompositionRepository.findAllByPkHouseholdSerialNumber(household.get().getSerialNumber()).size() > 1) {
            throw new RemainFamilyException();
        }
        residentRepository.deleteById(serialNumber);
    }
}
