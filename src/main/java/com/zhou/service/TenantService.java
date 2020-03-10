package com.zhou.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhou.mapper.OrdersMapper;
import com.zhou.mapper.OrdersMapperCustom;
import com.zhou.mapper.TenantsMapper;
import com.zhou.pojo.Orders;
import com.zhou.pojo.Tenants;
import com.zhou.pojo.Users;
import com.zhou.redis.HotelKey;
import com.zhou.redis.OrderKey;
import com.zhou.redis.RedisService;
import com.zhou.redis.RoomKey;
import com.zhou.utils.JSONResult;
import com.zhou.vo.OrdersVo;
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
public class TenantService {
    @Autowired
    private TenantsMapper tenantsMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public JSONResult createTenants(String[] realName, String[] idNumber,Integer roomId) {
       for(int i = 0;i<realName.length;i++){
           Tenants tenant = new Tenants();
           tenant.setRoomId(roomId);
           tenant.setRealName(realName[i]);
           tenant.setIdNumber(idNumber[i]);
           tenantsMapper.insert(tenant);
       }
        return JSONResult.ok();
    }
}
