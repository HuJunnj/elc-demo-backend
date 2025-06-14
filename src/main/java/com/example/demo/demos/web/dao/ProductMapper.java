package com.example.demo.demos.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.demos.web.model.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {}