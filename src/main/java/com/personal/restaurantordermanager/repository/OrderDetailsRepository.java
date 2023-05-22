package com.personal.restaurantordermanager.repository;

import com.personal.restaurantordermanager.model.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity, Integer> {

    List<OrderDetailsEntity> findByOrderId(Integer orderId);

    @Query("SELECT SUM(od.quantity * p.sellPrice) FROM OrderDetailsEntity od, ProductEntity p WHERE od.order.id = :orderId AND od.product.id = p.id")
    Double orderFinalValue(@Param("orderId") Integer orderId);
}
