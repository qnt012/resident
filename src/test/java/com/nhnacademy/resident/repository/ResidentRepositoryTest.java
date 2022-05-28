package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.BaseTest;
import com.nhnacademy.resident.entity.Resident;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ResidentRepositoryTest extends BaseTest {
    @Autowired
    ResidentRepository residentRepository;

    @Test
    void findAll() {
        List<Resident> residents = residentRepository.findAll();
        assertThat(residents).hasSize(7);
    }
}