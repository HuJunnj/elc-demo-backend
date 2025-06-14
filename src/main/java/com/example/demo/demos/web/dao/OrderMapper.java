package com.example.demo.demos.web.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.demos.web.model.Order;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface OrderMapper extends BaseMapper<Order> {}
