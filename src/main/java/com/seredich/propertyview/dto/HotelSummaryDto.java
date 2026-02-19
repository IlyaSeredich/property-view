package com.seredich.propertyview.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "Summary dto for hotel")
@Builder
public record HotelSummaryDto(
    Long id,
    String name,
    String description,
    String address,
    String phone
) {
}
