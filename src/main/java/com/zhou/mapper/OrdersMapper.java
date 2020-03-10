package com.zhou.mapper;

import com.zhou.pojo.Orders;
import com.zhou.pojo.OrdersExample;
import java.util.List;

import com.zhou.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrdersMapper extends MyMapper<Orders> {
}