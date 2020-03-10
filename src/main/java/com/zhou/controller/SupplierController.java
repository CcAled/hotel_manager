package com.zhou.controller;

import com.github.pagehelper.PageInfo;
import com.zhou.pojo.Rooms;
import com.zhou.pojo.Suppliers;
import com.zhou.redis.RedisService;
import com.zhou.service.HotelService;
import com.zhou.service.RoomService;
import com.zhou.service.SupplierService;
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
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

@Controller
@Slf4j
@Api(value = "供应商业务接口",tags = "供应商业务Contrller")
public class SupplierController extends BasicController{

    @Autowired
    private RoomService roomService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private SupplierService supplierService;


    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    RedisService redisService;


    @ApiOperation(value = "管理员登录页面", notes = "请求管理员登录页面接口")
    @GetMapping("/supplierLogin")
    public String toLogin() {
        return "supplierLogin";
    }


    @ApiOperation(value = "管理员登录", notes = "管理员登录接口")
    @PostMapping("/supplierLogin")
    @ResponseBody
    public JSONResult doLogin(String username,String password){

        Suppliers supplier = supplierService.login(username, password);
        if(supplier != null)return JSONResult.ok(supplier);
        else return JSONResult.errorMsg("用户名或密码不正确");
    }

    @ApiOperation(value = "供应商房型列表页面", notes = "获取供应商房型列表页面接口")
    @GetMapping("/supplierRoomsList")
    public String supplierRoomsList(Model model,@RequestParam Integer hotelId,@RequestParam(defaultValue = "1") Integer pageNum) {

        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<RoomsVo> pageInfo = hotelService.queryRoomsList(hotelId,pageNum,PAGE_SIZE);
        model.addAttribute("pageInfo",pageInfo);

        return "roomsListOld";
    }

    @ApiOperation(value = "修改房型信息", notes = "修改房型信息接口")
    @PostMapping("/changeRoomInfo")
    @ResponseBody
    public JSONResult changeRoomInfo(Rooms room){

        boolean issuccess = roomService.changeInfo(room);

        return JSONResult.ok("修改成功");
    }

    @ApiOperation(value = "添加房型信息", notes = "添加房型信息接口")
    @PostMapping("/addRoomInfo")
    @ResponseBody
    public JSONResult addRoomInfo(Rooms room){

        boolean issuccess = roomService.insertRoomInfo(room);

        return JSONResult.ok("添加成功");
    }


}
