package com.personal.restaurantordermanager.service;

import com.personal.restaurantordermanager.exception.ResourceNotFoundException;
import com.personal.restaurantordermanager.model.ProductEntity;
import com.personal.restaurantordermanager.model.dto.ProductDTO;
import com.personal.restaurantordermanager.model.dto.ProductMapper;
import com.personal.restaurantordermanager.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addAllProducts(List<ProductEntity> products) {
        productRepository.saveAll(products);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::entityToDto)
                .toList();
    }

    public ProductDTO getProductById(String id) {
        return ProductMapper.entityToDto(productRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Product with id %s was not found".formatted(id))));
    }

    public ProductEntity findProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id %s was not found".formatted(id)));
    }

    public ProductDTO createNewProduct(ProductDTO newProduct) {
        return ProductMapper.entityToDto(productRepository.save(ProductMapper.dtoToEntity(newProduct)));
    }

    public String deleteProductById(String id) {
        String response = getProductById(id).name();
        productRepository.deleteById(Integer.valueOf(id));
        return ("Product: " + response + " was deleted!");
    }

    public ProductDTO updateProduct(String id, ProductDTO updatedProduct) {
        ProductEntity initialProduct = ProductMapper.dtoToEntity(getProductById(id));
        initialProduct.setName(updatedProduct.name());
        initialProduct.setDescription(updatedProduct.description());
        initialProduct.setQuantity(updatedProduct.quantity());
        initialProduct.setSellPrice(updatedProduct.price());
        return ProductMapper.entityToDto(productRepository.save(initialProduct));
    }
}
