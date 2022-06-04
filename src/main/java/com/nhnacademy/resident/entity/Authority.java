package com.nhnacademy.resident.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "authority")
public class Authority {
    @Id
    @Column(name = "resident_serial_number")
    private Long serialNumber;

    @MapsId(value = "serialNumber")
    @OneToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    private String authority;
}
