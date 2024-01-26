package com.navulia.audit.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SupplierInputDTO {
    private final String name;
    private final String address;
    private final String contactDetails;
    private final String specialties;
}
