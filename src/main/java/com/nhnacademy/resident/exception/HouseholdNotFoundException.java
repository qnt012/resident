package com.nhnacademy.resident.exception;

public class HouseholdNotFoundException extends RuntimeException{
    public HouseholdNotFoundException() {
        super("속한 세대가 없습니다.");
    }
}
