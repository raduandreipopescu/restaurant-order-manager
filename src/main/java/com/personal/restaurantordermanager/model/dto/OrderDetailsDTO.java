package com.personal.restaurantordermanager.model.dto;

import lombok.Builder;

@Builder
public record OrderDetailsDTO(
        Integer id,
        Integer orderId,
        Integer productId,
        String productName,
        int quantity
) {
}
