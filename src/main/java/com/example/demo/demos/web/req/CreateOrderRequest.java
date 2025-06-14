package com.example.demo.demos.web.req;

import lombok.Data;

@Data
public class CreateOrderRequest {
    private Long productId;
    private Integer quantity;

    // Getters and Setters
}
