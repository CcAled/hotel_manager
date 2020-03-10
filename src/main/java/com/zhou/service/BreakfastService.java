package com.zhou.service;


import com.zhou.mapper.BreakfastMapper;
import com.zhou.pojo.Rooms;
import com.zhou.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhou.pojo.Breakfast;
import com.zhou.pojo.BreakfastExample;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BreakfastService {
    @Autowired
    private BreakfastMapper breakfastMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public Breakfast findBreakfastByhotelId(Integer hotel_id)
    {
        Breakfast breakfast = new Breakfast();
        breakfast.setHotelId(hotel_id);
        return breakfastMapper.selectOne(breakfast);
    }

//    @Transactional(propagation = Propagation.REQUIRED)
//    public JSONResult createBreakfast(Breakfast breakfast) {
//        Orders order = new Orders();
//        order.setCreateTime(new Date());
//        Integer hotelId = roomService.getBelongHotelId(roomId);
//        order.setHotelId(hotelId);
//        order.setPayment(roomService.getPriceByRoomId(roomId).intValue());
//        //TODO:数据库中订单表和房型表的价格数据类型不一致
//        order.setRoomId(roomId);
//        order.setState("未付");
//        order.setUserId(user.getUserId());
//        ordersMapper.insert(order);
//        roomService.descCount(roomId);
//        hotelService.descTotalCount(hotelId);
//        return JSONResult.ok(order);
//    }


}
