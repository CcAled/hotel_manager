package com.zhou.service;

import com.zhou.mapper.ManagersMapper;
import com.zhou.mapper.UsersMapper;
import com.zhou.pojo.Managers;
import com.zhou.pojo.Users;
import com.zhou.redis.CustomerKey;
import com.zhou.redis.RedisService;
import com.zhou.redis.UserKey;
import com.zhou.utils.MD5Util;
import com.zhou.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.zhou.constants.Constants.COOKI_NAME_TOKEN;

@Service
public class AdminService {

    @Autowired
    private ManagersMapper managersMapper;

    @Autowired
    private RedisService redisService;

    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean login(Integer id,String password) {
        if(id == null || password == null){
            return false;
        }
        Managers manager = new Managers();
        manager.setManagerId(id);
        manager.setManagerPassword(password);
        Managers result = managersMapper.selectOne(manager);
        if(result == null){
            return false;
        }
        return true;

    }

}
