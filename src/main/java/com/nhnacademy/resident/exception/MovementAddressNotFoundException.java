package com.nhnacademy.resident.exception;

public class MovementAddressNotFoundException extends RuntimeException{
    public MovementAddressNotFoundException() {
        super("전입주소기록이 없습니다.");
    }
}
