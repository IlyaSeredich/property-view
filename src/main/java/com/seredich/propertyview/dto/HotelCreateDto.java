package com.seredich.propertyview.dto;

import lombok.Builder;

@Builder
public record HotelCreateDto(
        String name,
        String description,
        String brand,
        AddressDto address,
        ContactDto contacts,
        ArrivalTimeDto arrivalTime
) {
}
