package com.nhnacademy.resident.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "household_movement_address")
public class MovementAddress {
    @EmbeddedId
    private Pk pk;

    @ManyToOne
    @MapsId(value = "householdSerialNumber")
    @JoinColumn(name = "household_serial_number")
    private Household household;

    @Column(name = "house_movement_address")
    private String address;

    @Column(name = "last_address_yn")
    private String lastAddressYn;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {
        private Long householdSerialNumber;
        @Column(name = "house_movement_report_date")
        private LocalDate reportDate;
    }
}
