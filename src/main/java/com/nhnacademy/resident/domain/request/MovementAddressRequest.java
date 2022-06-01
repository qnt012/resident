package com.nhnacademy.resident.domain.request;

import lombok.Data;

@Data
public class MovementAddressRequest {
    private String address;
    private String lastAddressYn;
}
