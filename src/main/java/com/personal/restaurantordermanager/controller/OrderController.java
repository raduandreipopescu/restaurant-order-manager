package com.personal.restaurantordermanager.controller;

import com.personal.restaurantordermanager.model.OrderEntity;
import com.personal.restaurantordermanager.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(value = "http://localhost:4200")
public class OrderController {
    private final OrderService service;

    @GetMapping
    public List<OrderEntity> getAllOrders() {
        log.info("GET all orders was called");
        return service.getAllOrders();
    }

    @PostMapping
    public OrderEntity createNewOrder(@RequestBody OrderEntity newOrder) {
        log.info("POST: Create new order was called");
        return service.createNewOrder(newOrder);
    }

    @PutMapping("{id}")
    public String closeOrder(@PathVariable String id) {
        log.info("PUT: Close order was called");
        return service.closeOrder(id);
    }
}
