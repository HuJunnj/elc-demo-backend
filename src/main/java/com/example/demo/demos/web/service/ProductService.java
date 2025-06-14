package com.example.demo.demos.web.service;

import com.example.demo.demos.web.dao.ProductMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.example.demo.demos.web.model.Product;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.*;
@CrossOrigin(origins = "http://localhost:10086")
@Service
public class ProductService {
    private final ProductMapper productMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String CACHE_KEY_PREFIX = "product:";

    public ProductService(ProductMapper productMapper, RedisTemplate<String, Object> redisTemplate) {
        this.productMapper = productMapper;
        this.redisTemplate = redisTemplate;
    }

    public List<Product> getAllProducts() {
        return productMapper.selectList(null);
    }

    public Product getProductById(Long id) {
        String cacheKey = CACHE_KEY_PREFIX + id;
        Product product = (Product) redisTemplate.opsForValue().get(cacheKey);

        if (product == null) {
            product = productMapper.selectById(id);
            if (product != null) {
                redisTemplate.opsForValue().set(cacheKey, product);
            }
        }
        return product;
    }

    public synchronized boolean reduceStock(Long id, int quantity) {
        Product product = getProductById(id);
        if (product == null || product.getStock() < quantity) {
            return false;
        }

        // 减库存并更新数据库
        product.setStock(product.getStock() - quantity);
        int updated = productMapper.updateById(product);

        if (updated > 0) {
            // 更新缓存
            redisTemplate.opsForValue().set(CACHE_KEY_PREFIX + id, product);
            return true;
        }
        return false;
    }
}
