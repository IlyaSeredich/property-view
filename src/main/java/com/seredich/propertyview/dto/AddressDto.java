package com.seredich.propertyview.dto;

import lombok.Builder;

@Builder
public record AddressDto(
        Integer houseNumber,
        String street,
        String city,
        String country,
        String postCode
) {
}
