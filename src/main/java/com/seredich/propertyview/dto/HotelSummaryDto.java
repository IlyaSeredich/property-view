package com.seredich.propertyview.dto;

public record HotelSummaryDto(
    Long id,
    String name,
    String description,
    String address,
    String phone
) {
}
