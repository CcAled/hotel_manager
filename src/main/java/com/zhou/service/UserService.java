package com.zhou.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhou.mapper.UsersMapper;
import com.zhou.pojo.Hotels;
import com.zhou.pojo.Users;
import com.zhou.redis.CustomerKey;
import com.zhou.redis.RedisService;
import com.zhou.redis.UserKey;
import com.zhou.utils.MD5Util;
import com.zhou.utils.UUIDUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.zhou.constants.Constants.COOKI_NAME_TOKEN;
import static com.zhou.controller.BasicController.PAGE_SIZE;

@Service
public class UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private RedisService redisService;

    public Users getByToken(HttpServletResponse response,String token) {
        if (StringUtils.isEmpty(token)){
            return null;
        }
        Users user =  redisService.get(UserKey.token,token,Users.class);
        //延长有效期
        //判断用户是否为空
        if(user != null){
            addCookie(response,token,user);
        }
        return user;
    }

    public Users getUserById(Integer userId) {
        Users user = new Users();
        user.setUserId(userId);
        Users result = usersMapper.selectOne(user);
        return result;
    }

    public boolean deleteByToken(String token) {
        if (StringUtils.isEmpty(token)){
            return false;
        }
        boolean success =  redisService.delete(UserKey.token,token);
        return success;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean queryUserIfExist(String username){
        if(username == null){
            return false;
        }
        Users user = new Users();
        user.setUsername(username);
        Users userResult = usersMapper.selectOne(user);
        return userResult==null?false:true;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer getUserIdByName(String username){
        if(username == null){
            return -1;
        }
        Users user = new Users();
        user.setUsername(username);
        Users userResult = usersMapper.selectOne(user);
        return userResult.getUserId();
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    public Users login(HttpServletResponse response, String username,String formPass) {
        if(username == null || formPass == null){
            return null;
        }
        Users user = new Users();
        user.setUsername(username);
        Users userResult = usersMapper.selectOne(user);
        if(userResult == null){
            return null;
        }


        //验证密码
        String dbPass = userResult.getPassword();
        String saltDB = userResult.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if(!calcPass.equals(dbPass)){
            return null;
        }
        //生成cookie
//        String token = UUIDUtil.uuid();
//        redisService.set(SeckillUserKey.token,token,user);
//        Cookie cookie = new Cookie(COOKI_NAME_TOKEN,token);
//        cookie.setMaxAge(SeckillUserKey.token.expireSeconds());
//        cookie.setPath("/");
//        response.addCookie(cookie);
        String token = UUIDUtil.uuid();
        addCookie(response,token,user);
        return userResult;
    }

    private void addCookie(HttpServletResponse response,String token,Users user){

//        redisService.set(SeckillUserKey.token,token,user);

        Cookie cookie = new Cookie(COOKI_NAME_TOKEN,token);
        cookie.setMaxAge(CustomerKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUser(Users user) {
        usersMapper.insert(user);
    }

    public boolean changeMyInfo(Users user) {
        usersMapper.updateByPrimaryKeySelective(user);
        return true;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PageInfo<Users> getAllUsers(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Users> list = usersMapper.selectAll();
        PageInfo<Users> pageList = new PageInfo<>(list);
        return pageList;
    }
}
