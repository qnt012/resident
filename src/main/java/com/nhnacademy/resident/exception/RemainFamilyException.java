package com.nhnacademy.resident.exception;

public class RemainFamilyException extends RuntimeException{
    public RemainFamilyException() {
        super("남은 가족이 있어 삭제가 불가능합니다.");
    }
}
