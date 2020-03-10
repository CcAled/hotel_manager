package com.zhou.controller;

import com.zhou.constants.Constants;
import com.zhou.pojo.Users;
import com.zhou.redis.RedisService;
import com.zhou.redis.UserKey;
import com.zhou.service.UserService;
import com.zhou.utils.JSONResult;
import com.zhou.utils.MD5Util;
import com.zhou.utils.UUIDUtil;
import com.zhou.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
@Api(value = "用户个人业务接口",tags = "用户个人业务Contrller")
public class LoginController extends BasicController{

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "用接户注册页面\", notes = \"请求用户注册页面口")
    @GetMapping("/regist")
    public String toRegist() {
        return "regist";
    }

    @ApiOperation(value = "用户登录页面", notes = "请求用户登录页面接口")
    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }

    @ApiOperation(value = "用户注册", notes = "用户注册接口")
    @PostMapping("/regist")
    @ResponseBody
    public JSONResult doRegist(@RequestBody Users user){
        log.info(user.toString());
        //判空
        if (org.thymeleaf.util.StringUtils.isEmpty(user.getUsername()) || org.thymeleaf.util.StringUtils.isEmpty(user.getPassword())) {
            return JSONResult.errorMsg("用户名和密码不能为空");
        }
        //判重
        boolean usernameIsExist = userService.queryUserIfExist(user.getUsername());
        //保存用户
        if (!usernameIsExist) {

            user.setUsername(user.getUsername());

            String salt = RandomStringUtils.randomAlphabetic(6);
            user.setSalt(salt);
            user.setPassword(MD5Util.formPassToDBPass(user.getPassword(),salt));
            //user.setPassword(user.getPassword());
            userService.saveUser(user);
        } else {
            return JSONResult.errorMsg("用户名已经存在");
        }
        user.setPassword(" ");
//        UsersVo userVO = setUserRedisSessionToken(user);
        return JSONResult.ok(user);
    }

    //将用户信息存在redis中，再次访问时从redis中取出token识别用户是否登录
//    public UsersVo setUserRedisSessionToken(Users userModel) {
//        String uniqueToken = UUID.randomUUID().toString();
//        redis.set(USER_REDIS_SESSION + ":" + userModel.getUserId(), uniqueToken, 1000 * 60 * 30);
//
//        UsersVo userVO = new UsersVo();
//        BeanUtils.copyProperties(userModel, userVO);
//        userVO.setUserToken(uniqueToken);
//        return userVO;
//    }


    @ApiOperation(value = "用户登录", notes = "用户登录接口")
    @PostMapping("/login")
    @ResponseBody
    public JSONResult doLogin(HttpServletResponse response, LoginVo loginVo){
        log.info(loginVo.toString());
        //参数校验
        String passInput = loginVo.getPassword();
        String username = loginVo.getUsername();
        if(StringUtils.isEmpty(passInput)){//为空，返回错误页面
            return JSONResult.errorMsg("密码为空！");
        }
        if(StringUtils.isEmpty(username)){//为空，返回错误页面
            return JSONResult.errorMsg("用户名为空！");
        }


        boolean usernameIsExist = userService.queryUserIfExist(loginVo.getUsername());
        if (usernameIsExist == false){
            return JSONResult.errorMsg("用户不存在！");
        }

        Users user = userService.login(response,loginVo.getUsername(),loginVo.getPassword());
        if (user==null){
           return JSONResult.errorMsg("用户名密码不正确！");
        }
//        UsersVo userVo = setUserRedisSessionToken(user);
        String token = UUIDUtil.uuid();
        addCookie(response,token,user);
        return JSONResult.ok("登陆成功");
    }

    private void addCookie(HttpServletResponse response,String token,Users user){

        redisService.set(UserKey.token,token,user);
        Cookie cookie = new Cookie(Constants.COOKI_NAME_TOKEN,token);
        cookie.setMaxAge(UserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }




}
