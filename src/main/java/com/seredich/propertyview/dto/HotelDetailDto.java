package com.seredich.propertyview.dto;

import java.util.List;

public record HotelDetailDto(
        Long id,
        String name,
        String brand,
        String description,
        AddressDto address,
        ContactDto contacts,
        ArrivalTimeDto arrivalTime,
        List<AmenityDto> amenities
) {
}
