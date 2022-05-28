package com.nhnacademy.resident.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "household")
public class Household {
    @Id
    @Column(name = "household_serial_number")
    private Long serialNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_resident_serial_number")
    private Resident resident;

    @Column(name = "household_composition_date")
    private LocalDate compositionDate;

    @Column(name = "household_composition_reason_code")
    private String compositionReasonCode;

    @Column(name = "current_house_movement_address")
    private String currentAddress;
}
