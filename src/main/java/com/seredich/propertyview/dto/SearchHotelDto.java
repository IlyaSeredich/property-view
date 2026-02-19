package com.seredich.propertyview.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record SearchHotelDto(
        String name,
        String brand,
        String city,
        String country,
        List<String> amenities
) {
}
