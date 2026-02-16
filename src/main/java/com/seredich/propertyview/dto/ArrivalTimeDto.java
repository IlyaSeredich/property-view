package com.seredich.propertyview.dto;

import jakarta.persistence.Column;

import java.time.LocalTime;

public record ArrivalTimeDto(
        LocalTime checkIn,
        LocalTime checkOut
) {
}
