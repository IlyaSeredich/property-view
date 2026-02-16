package com.seredich.propertyview.dto;

import java.util.List;

public record SearchHotelDto(
        String name,
        String brand,
        String city,
        String country,
        List<AmenityDto> amenities
) {
}
