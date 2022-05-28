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
public class BirthDeathReportResident {
    @EmbeddedId
    private Pk pk;

    @Column(name = "report_resident_serial_number")
    private Long reportResidentSerialNum;

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
        @Column(name = "resident_serial_number")
        private Long residentSerialNumber;
        @Column(name = "birth_death_type_code")
        private String typeCode;
    }
}
