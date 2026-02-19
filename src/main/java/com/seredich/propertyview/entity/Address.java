package com.seredich.propertyview.entity;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Column(nullable = false)
    private Integer houseNumber;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String postCode;
}
