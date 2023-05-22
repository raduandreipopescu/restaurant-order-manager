package com.personal.restaurantordermanager.controller;

import com.personal.restaurantordermanager.model.OrderDetailsEntity;
import com.personal.restaurantordermanager.model.OrderEntity;
import com.personal.restaurantordermanager.model.ProductEntity;
import com.personal.restaurantordermanager.model.dto.OrderDetailsDTO;
import com.personal.restaurantordermanager.service.OrderDetailsService;
import com.personal.restaurantordermanager.service.OrderService;
import com.personal.restaurantordermanager.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order-details")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(value = "http://localhost:4200")
public class OrderDetailsController {
    private final OrderDetailsService service;
    private final OrderService orderService;
    private final ProductService productService;

    @GetMapping("{id}")
    public List<OrderDetailsEntity> getAllOrderDetailsByOrderId(@PathVariable String id) {
        log.info("GET all order details by order id was called");
        return service.getAllOrderDetailsByOrderId(id);
    }

    @PostMapping
    public OrderDetailsDTO addOrderDetails(@RequestBody OrderDetailsDTO newOrderDetails) {
        log.info("POST: Add new order details was called");
        OrderEntity order = orderService.getOrderById(String.valueOf(newOrderDetails.orderId()));
        ProductEntity product = productService.findProductById(newOrderDetails.productId());
        return service.addOrderDetails(newOrderDetails, order, product);
    }

    @DeleteMapping("{id}")
    public String deleteOrderDetailsById(@PathVariable String id) {
        log.info("DELETE order details by id was called");
        return service.deleteOrderDetailsById(id);
    }

    @PutMapping("{id}")
    public OrderDetailsDTO updateOrderDetails(@PathVariable String id, @RequestBody OrderDetailsDTO updatedOrderDetails) {
        log.info("PUT: Update order details was called");
        return service.updateOrderDetails(id, updatedOrderDetails);
    }
}
