package com.seredich.propertyview.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;
}
