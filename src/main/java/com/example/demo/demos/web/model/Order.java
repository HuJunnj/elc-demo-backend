package com.example.demo.demos.web.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("orders")
public class Order {
    private Long id;
    private Long productId;
    private int quantity;
    private double totalPrice;
}
