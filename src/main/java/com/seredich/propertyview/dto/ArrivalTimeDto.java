package com.seredich.propertyview.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalTime;

@Builder
public record ArrivalTimeDto(
        @JsonFormat(pattern = "HH:mm")
        LocalTime checkIn,
        @JsonFormat(pattern = "HH:mm")
        LocalTime checkOut
) {
}
