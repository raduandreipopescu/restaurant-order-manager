package com.personal.restaurantordermanager.model.dto;

import lombok.Builder;

@Builder
public record ProductDTO(
        Integer id,
        String name,
        String description,
        int quantity,
        double price
) {
}
