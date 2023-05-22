package com.personal.restaurantordermanager;

import com.personal.restaurantordermanager.model.ProductEntity;
import com.personal.restaurantordermanager.service.ProductService;
import com.personal.restaurantordermanager.util.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandRunner implements CommandLineRunner {

    private final ProductService productService;

    @Override
    public void run(String... args) throws Exception {

        log.info("Runner is populating with data");

        FileUtils fileUtils = new FileUtils("src/main/resources/products.txt");

        List<String[]> products = fileUtils.putLinesFromFileToList();

        productService.addAllProducts(
                products.stream()
                        .map(line -> ProductEntity.builder()
                                .name(line[0])
                                .description(line[1])
                                .quantity(Integer.parseInt(line[2]))
                                .sellPrice(Double.parseDouble(line[3]))
                                .build())
                        .toList());
    }
}
