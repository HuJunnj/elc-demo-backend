package com.example.demo.demos.web.controller;

import com.example.demo.demos.web.model.Order;
import com.example.demo.demos.web.model.Product;
import com.example.demo.demos.web.req.CreateOrderRequest;
import com.example.demo.demos.web.service.OrderService;
import com.example.demo.demos.web.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final ProductService productService;
    private final OrderService orderService;

    public OrderController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }
    @Operation(summary = "创建订单", description = "根据商品 ID 和购买数量下单")
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest request) {
        try {
            Long productId = request.getProductId();
            Integer quantity = request.getQuantity();

            if (quantity <= 0) {
                return ResponseEntity.badRequest().body(Map.of("message", "购买数量必须大于0"));
            }

            Product product = productService.getProductById(productId);
            if (product == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "商品不存在"));
            }

            synchronized (this) {
                if (!productService.reduceStock(productId, quantity)) {
                    return ResponseEntity.badRequest().body(Map.of("message", "库存不足"));
                }
            }

            double totalPrice = product.getPrice() * quantity;
            Order order = orderService.createOrder(productId, quantity, totalPrice);

            return ResponseEntity.ok(Map.of(
                    "orderId", order.getId(),
                    "totalPrice", order.getTotalPrice()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "请求参数错误"));
        }
    }

    // Optional: 根据订单ID查询订单详情
    @Operation(summary = "查询订单", description = "根据订单 ID 获取订单详情")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}
