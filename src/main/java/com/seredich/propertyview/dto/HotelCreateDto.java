package com.seredich.propertyview.dto;

public record HotelCreateDto(
        String name,
        String description,
        String brand,
        AddressDto address,
        ContactDto contacts,
        ArrivalTimeDto arrivalTime
) {
}
