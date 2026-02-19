package com.seredich.propertyview.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Schema(description = "Detail dto for hotel")
@Builder
public record HotelDetailDto(
        Long id,
        String name,
        String description,
        String brand,
        AddressDto address,
        ContactDto contacts,
        ArrivalTimeDto arrivalTime,
        List<String> amenities
) {
}
