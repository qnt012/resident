package com.nhnacademy.resident.exception;

public class FamilyRelationshipNotFoundException extends RuntimeException{
    public FamilyRelationshipNotFoundException() {
        super("가족관계가 없습니다.");
    }
}
