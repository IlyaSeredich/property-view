package com.seredich.propertyview.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalTime;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArrivalTime {
    @Column(nullable = false)
    private LocalTime checkIn;

    private LocalTime checkOut;
}
