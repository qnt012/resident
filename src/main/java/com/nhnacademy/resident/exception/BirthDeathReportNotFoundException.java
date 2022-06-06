package com.nhnacademy.resident.exception;

public class BirthDeathReportNotFoundException extends RuntimeException{
    public BirthDeathReportNotFoundException() {
        super("출생, 사망기록이 없습니다.");
    }
}
