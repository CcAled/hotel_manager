package com.zhou.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhou.mapper.HotelsMapper;
import com.zhou.mapper.RoomsMapper;
import com.zhou.mapper.RoomsMapperCustom;
import com.zhou.pojo.Hotels;
import com.zhou.pojo.HotelsExample;
import com.zhou.pojo.Rooms;
import com.zhou.vo.RoomsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotelService {


    @Autowired
    private HotelsMapper hotelsMapper;

    @Autowired
    private RoomsMapperCustom roomsMapperCustom;


    @Transactional(propagation = Propagation.SUPPORTS)
    public PageInfo<Hotels> queryHotelsList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Hotels> list = hotelsMapper.selectAll();
        PageInfo<Hotels> pageList = new PageInfo<>(list);
        return pageList;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PageInfo<Hotels> queryQualifysList(Hotels hotel, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
//
        //搜索
        HotelsExample hotelExample = new HotelsExample();
        HotelsExample.Criteria hotelCriteria = hotelExample.createCriteria();
        //按酒店名模糊搜索
        if (StringUtils.isNotBlank(hotel.getHotelName())) {
            hotelCriteria.andHotelNameLike("%" + hotel.getHotelName() + "%");
        }
        //按地址模糊搜索
        if (StringUtils.isNotBlank(hotel.getAddress())) {
            hotelCriteria.andAddressLike("%" + hotel.getAddress() + "%");
        }
        //按酒店设施模糊搜索
        if (StringUtils.isNotBlank(hotel.getFacility())) {
            hotelCriteria.andFacilityLike("%" + hotel.getFacility() + "%");
        }
        //按酒店星级模糊搜索
        if (StringUtils.isNotBlank(hotel.getStarRate())) {
            hotelCriteria.andStarRateLike("%" + hotel.getStarRate() + "%");
        }
        //按地标景点模糊搜索
        if (StringUtils.isNotBlank(hotel.getSight())) {
            hotelCriteria.andSightLike("%" + hotel.getSight() + "%");
        }
        List<Hotels> hotelsList = hotelsMapper.selectByExample(hotelExample);
        //分页
        PageInfo<Hotels> pageList = new PageInfo<>(hotelsList);
        return pageList;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Hotels getHotelIdByHotelIdId(Integer hotelId) {
        Hotels hotel = new Hotels();
        hotel.setHotelId(hotelId);
        return hotelsMapper.selectOne(hotel);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PageInfo<RoomsVo> queryRoomsList(Integer hotelId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        RoomsVo room = new RoomsVo();
        room.setHotelId(hotelId);
        List<RoomsVo> list = roomsMapperCustom.queryRooms(hotelId);
        PageInfo<RoomsVo> pageList = new PageInfo<>(list);
        return pageList;
    }

    //减库存
    @Transactional(propagation = Propagation.REQUIRED)
    public void descTotalCount(Integer hotelId){
        //查询现在的
        Hotels example = new Hotels();
        example.setHotelId(hotelId);
        Hotels hotel = hotelsMapper.selectOne(example);

        //数量减少
        hotel.setTotalCount(hotel.getTotalCount()-1);

        //更新回去
        hotelsMapper.updateByPrimaryKeySelective(hotel);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String getHotelNameByHotelIdId(Integer hotelId) {
        Hotels hotel = new Hotels();
        hotel.setHotelId(hotelId);
        Hotels result = hotelsMapper.selectOne(hotel);
        return result.getHotelName();
    }

    public List<Hotels> listHotels() {
        return hotelsMapper.selectAll();
    }

    public boolean changeInfo(Hotels hotel) {
        hotelsMapper.updateByPrimaryKeySelective(hotel);
        return true;
    }

    public Integer getHotelIdByHotelName(String hotelName) {
        Hotels hotel = new Hotels();
        hotel.setHotelName(hotelName);

        Hotels result = hotelsMapper.selectOne(hotel);
        return result.getHotelId();
    }
}
