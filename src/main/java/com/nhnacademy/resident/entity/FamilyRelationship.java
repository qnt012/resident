package com.nhnacademy.resident.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "family_relationship")
public class FamilyRelationship {
    @EmbeddedId
    private Pk pk;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "baseResidentSerialNumber")
    @JoinColumn(name = "base_resident_serial_number")
    private Resident baseResident;

    @Column(name = "family_relationship_code")
    private String familyRelationshipCode;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {
        private Long baseResidentSerialNumber;
        @Column(name = "family_resident_serial_number")
        private Long familyResidentSerialNumber;
    }
}
