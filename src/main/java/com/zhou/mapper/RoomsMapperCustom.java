package com.zhou.mapper;

import com.zhou.pojo.Rooms;
import com.zhou.utils.MyMapper;
import com.zhou.vo.RoomsVo;

import java.util.List;

public interface RoomsMapperCustom extends MyMapper<Rooms> {
    public List<RoomsVo> queryRooms(Integer hotelId);
}