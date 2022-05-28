package com.nhnacademy.resident.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "certificate_issue")
public class CertificateIssue {
    @Id
    @Column(name = "certificate_confirmation_number")
    private Long confirmationNumber;

    @Column(name = "resident_serial_number")
    private Long residentSerialNumber;

    @Column(name = "certificate_type_code")
    private String typeCode;

    @Column(name = "certificate_issue_date")
    private LocalDate issueDate;
}
