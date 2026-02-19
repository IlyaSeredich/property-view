package com.seredich.propertyview.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "Dto for creating new hotel")
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
