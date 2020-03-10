package com.zhou.vo;


import com.zhou.pojo.Orders;
import com.zhou.service.RoomService;
import lombok.Data;

@Data
public class OrderDetailVo {
    private RoomsVo roomsVo;
    private Orders order;
}