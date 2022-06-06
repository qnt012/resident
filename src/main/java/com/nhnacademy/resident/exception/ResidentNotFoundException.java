package com.nhnacademy.resident.exception;

public class ResidentNotFoundException extends RuntimeException{
    public ResidentNotFoundException() {
        super("주민을 찾을 수 없습니다.");
    }
}
