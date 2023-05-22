package com.personal.restaurantordermanager.repository;

import com.personal.restaurantordermanager.model.OrderEntity;
import com.personal.restaurantordermanager.model.OrderStatus;
import com.personal.restaurantordermanager.model.TableName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    boolean existsByTableNameAndStatus(TableName tableName, OrderStatus orderStatus);
}
