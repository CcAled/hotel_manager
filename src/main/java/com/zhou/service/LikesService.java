package com.zhou.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhou.mapper.LikesMapper;
import com.zhou.mapper.OrdersMapper;
import com.zhou.pojo.Likes;
import com.zhou.pojo.Orders;
import com.zhou.pojo.Users;
import com.zhou.redis.HotelKey;
import com.zhou.redis.OrderKey;
import com.zhou.redis.RedisService;
import com.zhou.redis.RoomKey;
import com.zhou.utils.JSONResult;
import com.zhou.vo.RoomsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class LikesService {

    @Autowired
    private LikesMapper likesMapper;


    @Transactional(propagation = Propagation.SUPPORTS)
    public PageInfo<Likes> queryHotelListById(Integer userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        Likes likes = new Likes();
        likes.setUserId(userId);

        List<Likes> list = likesMapper.select(likes);
        PageInfo<Likes> pageList = new PageInfo<>(list);
        return pageList;
    }
}
