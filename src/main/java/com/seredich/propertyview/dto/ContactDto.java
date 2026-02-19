package com.seredich.propertyview.dto;

import lombok.Builder;

@Builder
public record ContactDto(
        String phone,
        String email
) {
}
