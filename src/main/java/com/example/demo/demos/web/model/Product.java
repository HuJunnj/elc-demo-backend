package com.example.demo.demos.web.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("product")
public class Product {
    private Long id;
    private String name;
    private double price;
    private int stock;
}
