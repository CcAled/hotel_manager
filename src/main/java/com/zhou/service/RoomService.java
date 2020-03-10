package com.zhou.service;

import com.zhou.mapper.OrdersMapper;
import com.zhou.mapper.RoomsMapper;
import com.zhou.pojo.Hotels;
import com.zhou.pojo.Rooms;
import com.zhou.vo.RoomsVo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomsMapper roomsMapper;

    @Autowired
    private HotelService hotelService;

    //减库存
    public void descCount(Integer roomId){
        //查询现在的
        Rooms example = new Rooms();
        example.setRoomsId(roomId);
        Rooms room = roomsMapper.selectOne(example);

        //数量减少
        room.setCount(room.getCount()-1);

        //更新回去
        roomsMapper.updateByPrimaryKeySelective(room);
    }

    //查询价格
    @Transactional(propagation = Propagation.SUPPORTS)
    public Float getPriceByRoomId(Integer roomId){
        //查询现在的
        Rooms example = new Rooms();
        example.setRoomsId(roomId);
        Rooms room = roomsMapper.selectOne(example);
        return room.getPrice();
    }

    //查询所属酒店id
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer getBelongHotelId(Integer roomId){
        //查询现在的
        Rooms example = new Rooms();
        example.setRoomsId(roomId);
        Rooms room = roomsMapper.selectOne(example);
        return room.getHotelId();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public RoomsVo getRoomVoByRoomId(Integer roomId) {
        RoomsVo roomsVo = new RoomsVo();
        //1.获取房间信息
        Rooms room = new Rooms();
        room.setRoomsId(roomId);
        Rooms roomInfo = roomsMapper.selectOne(room);
        roomsVo.setHotelId(roomInfo.getHotelId());
        roomsVo.setCount(roomInfo.getCount());
        roomsVo.setFacility(roomInfo.getFacility());
        roomsVo.setPrice(roomInfo.getPrice());
        roomsVo.setSize(roomInfo.getSize());
        roomsVo.setType(roomInfo.getType());
        //2.酒店名
        String hotelName = hotelService.getHotelNameByHotelIdId(roomInfo.getHotelId());
        roomsVo.setHotelName(hotelName);
        return roomsVo;

    }

    public List<Rooms> listRooms() {
        return roomsMapper.selectAll();
    }

    public boolean changeInfo(Rooms room) {
        roomsMapper.updateByPrimaryKeySelective(room);
        return true;
    }

    public boolean insertRoomInfo(Rooms room) {
        roomsMapper.insert(room);
        return true;
    }
}
