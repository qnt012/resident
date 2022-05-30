package com.nhnacademy.resident.domain.request;

import lombok.Data;

@Data
public class RelationshipCreateRequest {
    private Long familySerialNumber;
    private String relationship;
}
