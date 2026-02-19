package com.seredich.propertyview.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record HotelDetailDto(
        Long id,
        String name,
        String brand,
        String description,
        AddressDto address,
        ContactDto contacts,
        ArrivalTimeDto arrivalTime,
        List<String> amenities
) {
}
