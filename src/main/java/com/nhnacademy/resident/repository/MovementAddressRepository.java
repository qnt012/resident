package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.entity.MovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementAddressRepository extends JpaRepository<MovementAddress, MovementAddress.Pk> {
}
