package com.zhou.controller;

import com.github.pagehelper.PageInfo;
import com.zhou.config.CurrentUser;
import com.zhou.interceptor.AccessLimit;
import com.zhou.pojo.Hotels;
import com.zhou.pojo.Users;
import com.zhou.redis.HotelKey;
import com.zhou.redis.RedisService;
import com.zhou.redis.RoomKey;
import com.zhou.service.HotelService;
import com.zhou.service.UserService;
import com.zhou.utils.JSONResult;
import com.zhou.vo.RoomsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
@Api(value = "酒店业务接口",tags = "酒店业务Contrller")
public class HotelController extends BasicController{

    @Autowired
    private UserService userService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    RedisService redisService;



    @ApiOperation(value = "酒店列表页面", notes = "请求酒店列表页面接口")
    @GetMapping("/hotelList")
    @ResponseBody
    @AccessLimit(seconds = 5,maxCount = 5,needLogin = false)
    public String queryHotelList(HttpServletRequest request,HttpServletResponse response,@CurrentUser Users user,Model model, @RequestParam(defaultValue = "1") Integer pageNum) {
        //取缓存
        String html = redisService.get(HotelKey.getHotelsList,"",String.class);

        if (user == null){
            log.info("未登录");
        }else{
            log.info(user.toString());
        }

        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<Hotels> pageInfo = hotelService.queryHotelsList(pageNum,PAGE_SIZE);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("user",user);

        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request, response,
                request.getServletContext(), request.getLocale(), model.asMap(), applicationContext);
        html = thymeleafViewResolver.getTemplateEngine().process("hotelList", ctx);
        //不为空就存到redis中去
        if(!StringUtils.isEmpty(html)){
            redisService.set(HotelKey.getHotelsList,"",html);
        }
        //返回页面
        return html;

    }

    @ApiOperation(value = "搜索酒店页面", notes = "搜索酒店接口")
    @GetMapping("/searchHotelList")
    @ResponseBody
    public String searchHotelList(HttpServletRequest request,HttpServletResponse response,Model model, Hotels hotel, @RequestParam(defaultValue = "1") Integer pageNum) {
        //取缓存
        String html = redisService.get(HotelKey.searchHotelList,"name="+hotel.getHotelName()+"star="+hotel.getStarRate()+"sight="+hotel.getSight()+"address="+hotel.getAddress(),String.class);
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<Hotels> pageInfo = hotelService.queryQualifysList(hotel,pageNum,PAGE_SIZE);
        model.addAttribute("pageInfo",pageInfo);

        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request, response,
                request.getServletContext(), request.getLocale(), model.asMap(), applicationContext);
        html = thymeleafViewResolver.getTemplateEngine().process("queryHotelList", ctx);
        //不为空就存到redis中去
        if(!StringUtils.isEmpty(html)){
            redisService.set(HotelKey.searchHotelList,"name="+hotel.getHotelName()+"star="+hotel.getStarRate()+"sight="+hotel.getSight()+"address="+hotel.getAddress(),html);
        }
        //返回页面
        return html;
//        return "queryHotelList";
    }

    @ApiOperation(value = "酒店详情页面", notes = "获取酒店详情接口")
    @GetMapping(value = "/detail/{hotelId}")
    @ResponseBody
    public JSONResult detail(HttpServletRequest request, HttpServletResponse response,
                             Model model, @PathVariable("hotelId")Integer hotelId) {
        Hotels hotel = hotelService.getHotelIdByHotelIdId(hotelId);
        return JSONResult.ok(hotel);
    }

    @ApiOperation(value = "房型列表页面", notes = "获取房型列表页面接口")
    @ResponseBody
    @GetMapping("/roomsList")
    public String queryRoomsList(HttpServletRequest request,HttpServletResponse response,Model model,@RequestParam Integer hotelId,@RequestParam(defaultValue = "1") Integer pageNum) {
        //取缓存
        String html = redisService.get(RoomKey.getRoomsList,hotelId.toString(),String.class);
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<RoomsVo> pageInfo = hotelService.queryRoomsList(hotelId,pageNum,PAGE_SIZE);
        model.addAttribute("pageInfo",pageInfo);

        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request, response,
                request.getServletContext(), request.getLocale(), model.asMap(), applicationContext);
        html = thymeleafViewResolver.getTemplateEngine().process("rooms", ctx);
        //不为空就存到redis中去
        if(!StringUtils.isEmpty(html)){
            redisService.set(RoomKey.getRoomsList,hotelId.toString(),html);
        }
        //返回页面
        return html;
//        return "roomListnew";
    }



}
