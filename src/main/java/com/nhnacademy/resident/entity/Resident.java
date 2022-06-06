package com.nhnacademy.resident.entity;

import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "resident")
@JsonIgnoreProperties({"authority","householdList", "householdCompositions", "birthDeathReports", "reportBirthDeathReports", "familyRelationshipList"})
public class Resident {
    @Id
    @Column(name = "resident_serial_number")
    private Long serialNumber;

    private String name;

    @Column(name = "resident_registration_number")
    private String registrationNumber;

    @Column(name = "gender_code")
    private String genderCode;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "birth_place_code")
    private String birthPlaceCode;

    @Column(name = "registration_base_address")
    private String registrationBaseAddress;

    @Column(name = "death_date")
    private LocalDateTime deathDate;

    @Column(name = "death_place_code")
    private String deathPlaceCode;

    @Column(name = "death_place_address")
    private String deathPlaceAddress;

    @Column(name = "id")
    private String residentId;

    private String pwd;

    private String email;

    @OneToOne(mappedBy = "resident", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    private Authority authority;

    @OneToMany(mappedBy = "resident", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Household> householdList;

    @OneToMany(mappedBy = "resident", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<HouseholdComposition> householdCompositions;

    @OneToMany(mappedBy = "resident", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<BirthDeathReport> birthDeathReports;

    @OneToMany(mappedBy = "reportResident", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<BirthDeathReport> reportBirthDeathReports;

    @OneToMany(mappedBy = "baseResident", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<FamilyRelationship> familyRelationshipList;

    public Resident(Long serialNumber, String name, String registrationNumber,
                    String genderCode, LocalDateTime birthDate, String birthPlaceCode,
                    String registrationBaseAddress, LocalDateTime deathDate,
                    String deathPlaceCode, String deathPlaceAddress,
                    String id, String pwd, String email) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.genderCode = genderCode;
        this.birthDate = birthDate;
        this.birthPlaceCode = birthPlaceCode;
        this.registrationBaseAddress = registrationBaseAddress;
        this.deathDate = deathDate;
        this.deathPlaceCode = deathPlaceCode;
        this.deathPlaceAddress = deathPlaceAddress;
        this.residentId = id;
        this.pwd = pwd;
        this.email = email;
    }
}
