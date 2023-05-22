package com.personal.restaurantordermanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private OrderEntity order;

    @OneToOne
    private ProductEntity product;

    @Column
    private Integer quantity;
}
