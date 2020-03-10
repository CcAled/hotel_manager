package com.zhou.mapper;

import com.zhou.pojo.Orders;
import com.zhou.utils.MyMapper;
import com.zhou.vo.OrderDetailVo;
import com.zhou.vo.OrdersVo;
import com.zhou.vo.RoomsVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrdersMapperCustom extends MyMapper<OrdersVo> {
    public List<OrdersVo> queryMyOrders(Integer userId);

    public List<OrdersVo> selectAllOrdersList();

}