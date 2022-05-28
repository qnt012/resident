package com.nhnacademy.resident.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "household_composition_resident")
public class HouseholdComposition {
    @EmbeddedId
    private Pk pk;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "householdSerialNumber")
    @JoinColumn(name = "household_serial_number")
    private Household household;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "residentSerialNumber")
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @Column(name = "report_date")
    private LocalDate reportDate;

    @Column(name = "household_relationship_code")
    private String relationshipCode;

    @Column(name = "household_composition_change_reason_code")
    private String changeReason;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {
        private Long householdSerialNumber;
        private Long residentSerialNumber;
    }
}
