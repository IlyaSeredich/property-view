package com.seredich.propertyview.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Contact {
    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;
}
