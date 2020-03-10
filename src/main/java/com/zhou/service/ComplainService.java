package com.zhou.service;

import com.zhou.mapper.ComplainsMapper;
import com.zhou.mapper.SuppliersMapper;
import com.zhou.pojo.Complains;
import com.zhou.pojo.Suppliers;
import com.zhou.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComplainService {

    @Autowired
    private ComplainsMapper complainsMapper;

    @Autowired
    private RedisService redisService;

    @Transactional(propagation = Propagation.SUPPORTS)
    public Complains createComplain(Integer userId, Integer hotelId, String content) {
        Complains complain = new Complains();
        complain.setHotelId(hotelId);
        complain.setUserId(userId);
        complain.setContent(content);
        complainsMapper.insert(complain);
        return complain;
    }




}
