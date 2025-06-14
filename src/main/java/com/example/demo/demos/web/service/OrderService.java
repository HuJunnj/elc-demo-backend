package com.example.demo.demos.web.service;

import com.example.demo.demos.web.dao.OrderMapper;
import com.example.demo.demos.web.model.Order;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderService {
    private final OrderMapper orderMapper;

    public OrderService(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public Order createOrder(Long productId, int quantity, double totalPrice) {
        Order order = new Order();
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setTotalPrice(totalPrice);
        orderMapper.insert(order);
        return order;
    }

    public Order getOrderById(Long id) {
        return orderMapper.selectById(id);
    }
}