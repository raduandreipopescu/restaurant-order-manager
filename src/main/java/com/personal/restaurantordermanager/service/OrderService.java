package com.personal.restaurantordermanager.service;

import com.personal.restaurantordermanager.exception.ResourceAlreadyExistsException;
import com.personal.restaurantordermanager.exception.ResourceNotFoundException;
import com.personal.restaurantordermanager.model.OrderEntity;
import com.personal.restaurantordermanager.model.OrderStatus;
import com.personal.restaurantordermanager.repository.OrderDetailsRepository;
import com.personal.restaurantordermanager.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity getOrderById(String id) {
        return orderRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Order with id %s was not found".formatted(id)));
    }

    public OrderEntity createNewOrder(OrderEntity newOrder) {
        if (orderRepository.existsByTableNameAndStatus(newOrder.getTableName(), OrderStatus.OPEN)) {
            throw new ResourceAlreadyExistsException("Already exists an open order for table %s".formatted(newOrder.getTableName()));
        }
        newOrder.setStatus(OrderStatus.OPEN);
        return orderRepository.save(newOrder);
    }

    public String closeOrder(String id) {
        OrderEntity order = getOrderById(id);
        if (order.getStatus().equals(OrderStatus.CLOSED)) {
            throw new ResourceAlreadyExistsException("Order is already closed");
        }
        order.setFinalValue(orderDetailsRepository.orderFinalValue(order.getId()));
        order.setStatus(OrderStatus.CLOSED);
        orderRepository.save(order);
        return "Order was closed";
    }
}
