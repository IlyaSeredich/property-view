package com.seredich.propertyview.dto;

import lombok.Builder;

import java.time.LocalTime;

@Builder
public record ArrivalTimeDto(
        LocalTime checkIn,
        LocalTime checkOut
) {
}
