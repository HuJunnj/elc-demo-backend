package com.example.demo.demos.web.controller;


import com.example.demo.demos.web.model.Product;
import com.example.demo.demos.web.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(summary = "获取所有商品")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }
}
