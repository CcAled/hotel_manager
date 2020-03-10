package com.zhou.controller;

import com.github.pagehelper.PageInfo;
import com.zhou.config.CurrentUser;
import com.zhou.constants.Constants;
import com.zhou.exception.CodeMsg;
import com.zhou.interceptor.AccessLimit;
import com.zhou.pojo.Complains;
import com.zhou.pojo.Likes;
import com.zhou.pojo.Users;
import com.zhou.redis.RedisService;
import com.zhou.service.ComplainService;
import com.zhou.service.HotelService;
import com.zhou.service.LikesService;
import com.zhou.service.UserService;
import com.zhou.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
@Api(value = "用户个人业务接口",tags = "用户个人业务Contrller")
public class UserController extends BasicController{

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private LikesService likesService;

    @Autowired
    private ComplainService complainService;
    @Autowired
    private HotelService hotelService;

    @ApiOperation(value = "获取个人页面", notes = "获取个人页面接口")
    @GetMapping("/myInfo/{userId}")
    @ResponseBody
    //@NeedLogin
    //TODO 拦截器
    public JSONResult myInfo(Model model,@PathVariable("userId") Integer userId) {
        if (userId == null){
            return JSONResult.errorMap(CodeMsg.SESSION_ERROR);
        }
        Users user = userService.getUserById(userId);
        return JSONResult.ok(user);
    }

    @ApiOperation(value = "注销当前用户", notes = "注销当前用户接口")
    @GetMapping("/logout")
    @ResponseBody
    //@NeedLogin
    //TODO 拦截器
    @AccessLimit(seconds = 5,maxCount = 5,needLogin = false)
    public String logout(HttpServletRequest request,HttpServletResponse response,@CurrentUser Users user) throws IOException {
        if (user == null){
            return null;
        }
        String cookieToken = getCookieValue(request,Constants.COOKI_NAME_TOKEN);
        userService.deleteByToken(cookieToken);
        response.sendRedirect(request.getContextPath() + "/hotelList");
        return "hotelList";
    }

    private String getCookieValue(HttpServletRequest request, String cookiName) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null || cookies.length <= 0){
            return null;
        }
        for (Cookie cookie:cookies){
            if(cookie.getName().equals(cookiName)){
                return cookie.getValue();
            }
        }
        return null;
    }

    @ApiOperation(value = "修改个人信息", notes = "修改个人信息接口")
    @GetMapping("/changeMyInfo")
    @ResponseBody
    public JSONResult changeMyInfo(Integer userId,String username,String sex,String phone,String email,String pwQuestion,String pwAnswer){

        Users user = userService.getUserById(userId);
        user.setUsername(username);
        user.setSex(sex);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPwQuestion(pwQuestion);
        user.setPwAnswer(pwAnswer);

        boolean issuccess = userService.changeMyInfo(user);

        return JSONResult.ok("修改成功");
    }

    @ApiOperation(value = "我的收藏页面", notes = "获取我的收藏页面接口")
    @GetMapping("/myLikes")
    @ResponseBody
    //测试中
    //public String myOrder(Model model,@RequestParam Integer userId,@RequestParam(defaultValue = "1") Integer pageNum)
    public JSONResult myLikes(@RequestParam Integer userId,@RequestParam(defaultValue = "1") Integer pageNum) {

        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<Likes> pageInfo = likesService.queryHotelListById(userId,pageNum,PAGE_SIZE);
//        model.addAttribute("pageInfo",pageInfo);

        return JSONResult.ok(pageInfo);
//        return "myOrder";
    }

    @ApiOperation(value = "提交投诉", notes = "提交投诉接口")
    @GetMapping("/Complain")
    @ResponseBody
    public JSONResult postComplain(@RequestParam String username,@RequestParam String hotelName,@RequestParam String content) {
        Integer userId = userService.getUserIdByName(username);
        Integer hotelId = hotelService.getHotelIdByHotelName(hotelName);
        Complains complain = complainService.createComplain(userId, hotelId, content);
        return JSONResult.ok(complain);
    }



}
