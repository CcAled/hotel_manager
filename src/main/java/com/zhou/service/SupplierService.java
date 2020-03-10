package com.zhou.service;

import com.zhou.mapper.ManagersMapper;
import com.zhou.mapper.SuppliersMapper;
import com.zhou.pojo.Managers;
import com.zhou.pojo.Suppliers;
import com.zhou.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupplierService {

    @Autowired
    private SuppliersMapper suppliersMapper;

    @Autowired
    private RedisService redisService;

    @Transactional(propagation = Propagation.SUPPORTS)
    public Suppliers login(String username,String password) {
        if(username == null || password == null){
            return null;
        }
        Suppliers supplier = new Suppliers();
        supplier.setUsername(username);
        supplier.setPassword(password);
        Suppliers result = suppliersMapper.selectOne(supplier);
        return result;

    }

}
