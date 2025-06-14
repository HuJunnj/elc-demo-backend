-- 创建商品表（如果不存在）
CREATE TABLE IF NOT EXISTS product (
                                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                       name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    stock INT NOT NULL
    );

-- 插入商品数据（避免重复插入）
INSERT INTO product (name, price, stock)
SELECT * FROM (
                  SELECT 'iPhone 15 Pro', 9999.00, 20 UNION ALL
                  SELECT 'MacBook Air M3', 12999.00, 15 UNION ALL
                  SELECT 'iPad Pro 12.9"', 8999.00, 10 UNION ALL
                  SELECT 'AirPods Pro 2', 1899.00, 50 UNION ALL
                  SELECT 'Apple Watch S9', 3399.00, 30 UNION ALL
                  SELECT 'Dell XPS 13', 8599.00, 8
              ) AS tmp(name, price, stock)
WHERE NOT EXISTS (
    SELECT 1 FROM product
);

-- 创建订单表（如果不存在）
CREATE TABLE IF NOT EXISTS orders (
                                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                      product_id BIGINT NOT NULL,
                                      quantity INT NOT NULL,
                                      total_price DOUBLE NOT NULL
);