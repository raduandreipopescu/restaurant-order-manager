package com.personal.restaurantordermanager.model.dto;

import com.personal.restaurantordermanager.model.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public static ProductDTO entityToDto(ProductEntity productEntity) {
        return ProductDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .quantity(productEntity.getQuantity())
                .price(productEntity.getSellPrice())
                .build();
    }

    public static ProductEntity dtoToEntity(ProductDTO productDTO) {
        return ProductEntity.builder()
                .id(productDTO.id())
                .name(productDTO.name())
                .description(productDTO.description())
                .quantity(productDTO.quantity())
                .sellPrice(productDTO.price())
                .build();
    }
}
