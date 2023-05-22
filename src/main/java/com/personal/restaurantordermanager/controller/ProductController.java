package com.personal.restaurantordermanager.controller;

import com.personal.restaurantordermanager.model.dto.ProductDTO;
import com.personal.restaurantordermanager.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(value = "http://localhost:4200")
public class ProductController {
    private final ProductService service;

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        log.info("GET all products was called");
        return service.getAllProducts();
    }

    @GetMapping("{id}")
    public ProductDTO getProductById(@PathVariable String id) {
        log.info("GET product by id was called");
        return service.getProductById(id);
    }

    @PostMapping
    public ProductDTO createNewProduct(@RequestBody ProductDTO newProduct) {
        log.info("POST: Create new product was called");
        return service.createNewProduct(newProduct);
    }

    @PutMapping("{id}")
    public ProductDTO updateProduct(@PathVariable String id, @RequestBody ProductDTO updatedProduct) {
        log.info("PUT: Update product was called");
        return service.updateProduct(id, updatedProduct);
    }

    @DeleteMapping("{id}")
    public String deleteProductById(@PathVariable String id) {
        log.info("DELETE product by id was called");
        return service.deleteProductById(id);
    }
}
