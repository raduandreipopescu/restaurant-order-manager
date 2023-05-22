package com.personal.restaurantordermanager.service;

import com.personal.restaurantordermanager.exception.ResourceAlreadyExistsException;
import com.personal.restaurantordermanager.exception.ResourceNotFoundException;
import com.personal.restaurantordermanager.model.OrderDetailsEntity;
import com.personal.restaurantordermanager.model.OrderEntity;
import com.personal.restaurantordermanager.model.OrderStatus;
import com.personal.restaurantordermanager.model.ProductEntity;
import com.personal.restaurantordermanager.model.dto.OrderDetailsDTO;
import com.personal.restaurantordermanager.model.dto.OrderDetailsMapper;
import com.personal.restaurantordermanager.repository.OrderDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;

    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository, OrderService orderService, ProductService productService) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public List<OrderDetailsEntity> getAllOrderDetailsByOrderId(String id) {
        return orderDetailsRepository.findByOrderId(Integer.valueOf(id));
    }

    public OrderDetailsEntity getOrderDetailsById(String id) {
        return orderDetailsRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Order details were not found"));
    }

    public OrderDetailsDTO addOrderDetails(OrderDetailsDTO newOrderDetails, OrderEntity order, ProductEntity product) {
        if (order.getStatus().equals(OrderStatus.CLOSED)) {
            throw new ResourceAlreadyExistsException("Order is closed!");
        }
        OrderDetailsEntity orderDetailsEntity = OrderDetailsMapper.dtoToEntity(newOrderDetails, order, product);
        return OrderDetailsMapper.entityToDto(orderDetailsRepository.save(orderDetailsEntity));
    }

    public String deleteOrderDetailsById(String id) {
        getOrderDetailsById(id);
        orderDetailsRepository.deleteById(Integer.valueOf(id));
        return ("Order details were deleted!");
    }

    public OrderDetailsDTO updateOrderDetails(String id, OrderDetailsDTO updatedOrderDetails) {
        OrderDetailsEntity initialOrderDetails = getOrderDetailsById(id);
        initialOrderDetails.setQuantity(updatedOrderDetails.quantity());
        return OrderDetailsMapper.entityToDto(orderDetailsRepository.save(initialOrderDetails));
    }
}
