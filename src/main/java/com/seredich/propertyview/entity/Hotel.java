package com.seredich.propertyview.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    private String description;

    @Embedded
    private Address address;

    @Embedded
    private Contact contacts;

    @Embedded
    private ArrivalTime arrivalTime;

    @ManyToMany
    @JoinTable(
            name = "hotels_amenities",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private List<Amenity> amenities = new ArrayList<>();

}
