package com.nhnacademy.resident.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "birth_death_report_resident")
public class BirthDeathReport {
    @EmbeddedId
    private Pk pk;

    @ManyToOne
    @MapsId(value = "residentSerialNumber")
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @ManyToOne
    @JoinColumn(name = "report_resident_serial_number")
    private Resident reportResident;

    @Column(name = "birth_death_report_date")
    private LocalDate reportDate;

    @Column(name = "birth_report_qualifications_code")
    private String birthReportQualifyCode;

    @Column(name = "death_report_qualifications_code")
    private String deathReportQualifyCode;

    @Column(name = "email_address")
    private String email;

    @Column(name = "phone_number")
    private String phone;


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {
        private Long residentSerialNumber;
        @Column(name = "birth_death_type_code")
        private String typeCode;
    }
}
