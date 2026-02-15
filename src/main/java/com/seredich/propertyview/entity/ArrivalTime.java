package com.seredich.propertyview.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Embeddable
@Getter
@Setter
public class ArrivalTime {
    @Column(nullable = false)
    private LocalTime checkIn;

    private LocalTime checkOut;
}
