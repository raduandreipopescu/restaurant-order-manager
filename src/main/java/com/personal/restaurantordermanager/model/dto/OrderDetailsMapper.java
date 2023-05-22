package com.personal.restaurantordermanager.model.dto;

import com.personal.restaurantordermanager.model.OrderDetailsEntity;
import com.personal.restaurantordermanager.model.OrderEntity;
import com.personal.restaurantordermanager.model.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailsMapper {

    public static OrderDetailsDTO entityToDto(OrderDetailsEntity orderDetailsEntity) {
        return OrderDetailsDTO.builder()
                .id(orderDetailsEntity.getId())
                .orderId(orderDetailsEntity.getOrder().getId())
                .productId(orderDetailsEntity.getProduct().getId())
                .productName(orderDetailsEntity.getProduct().getName())
                .quantity(orderDetailsEntity.getQuantity())
                .build();
    }

    public static OrderDetailsEntity dtoToEntity(OrderDetailsDTO orderDetailsDTO, OrderEntity orderEntity, ProductEntity productEntity) {
        return OrderDetailsEntity.builder()
                .id(orderDetailsDTO.id())
                .order(orderEntity)
                .product(productEntity)
                .quantity(orderDetailsDTO.quantity())
                .build();
    }
}
