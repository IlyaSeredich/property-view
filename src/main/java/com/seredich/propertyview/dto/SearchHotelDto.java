package com.seredich.propertyview.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Schema(description = "Dto for search")
@Builder
public record SearchHotelDto(
        String name,
        String brand,
        String city,
        String country,
        List<String> amenities
) {
}
