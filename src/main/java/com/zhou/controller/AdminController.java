package com.zhou.controller;

import com.github.pagehelper.PageInfo;
import com.zhou.pojo.Hotels;
import com.zhou.pojo.Users;
import com.zhou.redis.RedisService;
import com.zhou.service.AdminService;
import com.zhou.service.HotelService;
import com.zhou.service.OrderService;
import com.zhou.service.UserService;
import com.zhou.utils.JSONResult;
import com.zhou.vo.OrdersVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
@Api(value = "管理员业务接口",tags = "管理员业务Contrller")
public class AdminController extends BasicController{

    @Autowired
    private UserService userService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    private RedisService redisService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "管理酒店列表页面", notes = "请求管理酒店列表页面接口")
    @GetMapping("/adminHotelList")
    public String queryHotelList(Model model, @RequestParam(defaultValue = "1") Integer pageNum) {
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<Hotels> pageInfo = hotelService.queryHotelsList(pageNum,PAGE_SIZE);
        model.addAttribute("pageInfo",pageInfo);
        return "adminHotelList";
    }

    @ApiOperation(value = "管理搜索酒店页面", notes = "管理搜索酒店接口")
    @GetMapping("/queryHotelList")
    @ResponseBody
    public String searchHotelList(HttpServletRequest request,HttpServletResponse response,Model model, Hotels hotel, @RequestParam(defaultValue = "1") Integer pageNum) {
       if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<Hotels> pageInfo = hotelService.queryQualifysList(hotel,pageNum,PAGE_SIZE);
        model.addAttribute("pageInfo",pageInfo);
        return "queryHotelList";
    }

    @ApiOperation(value = "修改酒店信息", notes = "修改酒店信息接口")
    @PostMapping("/changeInfo")
    @ResponseBody
    public JSONResult changeInfo(Hotels hotel){

        boolean issuccess = hotelService.changeInfo(hotel);

        return JSONResult.ok("修改成功");
    }

    @ApiOperation(value = "管理员登录页面", notes = "请求管理员登录页面接口")
    @GetMapping("/adminLogin")
    public String toLogin() {
        return "adminLogin";
    }


    @ApiOperation(value = "管理员登录", notes = "管理员登录接口")
    @PostMapping("/adminLogin")
    @ResponseBody
    public JSONResult doLogin(Integer id,String password){

        boolean isok = adminService.login(id, password);
        if(isok)return JSONResult.ok("登陆成功");
        else return JSONResult.errorMsg("用户名或密码不正确");
    }

    @ApiOperation(value = "管理用户页面", notes = "获取管理用户页面接口")
    @GetMapping("/usersList")
//    @ResponseBody
    //测试中
    //public String myOrder(Model model,@RequestParam Integer userId,@RequestParam(defaultValue = "1") Integer pageNum)
    public String usersList(Model model,@RequestParam(defaultValue = "1") Integer pageNum) {

        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<Users> pageInfo = userService.getAllUsers(pageNum,10);
        model.addAttribute("pageInfo",pageInfo);

//        return JSONResult.ok(pageInfo);
        return "usersList";
    }

    @ApiOperation(value = "管理订单页面", notes = "获取管理订单页面接口")
    @GetMapping("/ordersList")
//    @ResponseBody
    //测试中
    //public String myOrder(Model model,@RequestParam Integer userId,@RequestParam(defaultValue = "1") Integer pageNum)
    public String ordersList(Model model,@RequestParam(defaultValue = "1") Integer pageNum) {

        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<OrdersVo> pageInfo = orderService.getAllOrdersVo(pageNum,PAGE_SIZE);
        model.addAttribute("pageInfo",pageInfo);

//        return JSONResult.ok(pageInfo);
        return "ordersList";
    }
}
