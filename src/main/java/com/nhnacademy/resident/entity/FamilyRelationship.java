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

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "familyResidentSerialNumber")
    @JoinColumn(name = "family_resident_serial_number")
    private Resident familyResident;

    @Column(name = "family_relationship_code")
    private String relationshipCode;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {
        private Long baseResidentSerialNumber;
        private Long familyResidentSerialNumber;
    }
}
