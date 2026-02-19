package com.seredich.propertyview.dto;

import lombok.Builder;

@Builder
public record HotelSummaryDto(
    Long id,
    String name,
    String description,
    String address,
    String phone
) {
}
