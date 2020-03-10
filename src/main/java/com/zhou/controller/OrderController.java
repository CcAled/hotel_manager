package com.zhou.controller;


import com.github.pagehelper.PageInfo;
import com.zhou.config.CurrentUser;
import com.zhou.exception.CodeMsg;
import com.zhou.interceptor.AccessLimit;
import com.zhou.pojo.Hotels;
import com.zhou.pojo.Orders;
import com.zhou.pojo.Rooms;
import com.zhou.pojo.Users;
import com.zhou.redis.HotelKey;
import com.zhou.redis.RedisService;
import com.zhou.service.HotelService;
import com.zhou.service.OrderService;
import com.zhou.service.RoomService;
import com.zhou.service.TenantService;
import com.zhou.utils.JSONResult;
import com.zhou.vo.OrderDetailVo;
import com.zhou.vo.OrdersVo;
import com.zhou.vo.RoomsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhou.controller.BasicController.PAGE_SIZE;

@Controller
@Slf4j
@Api(value = "订单业务接口",tags = "订单业务Contrller")
public class OrderController implements InitializingBean {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private  HotelService hotelService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private TenantService tenantService;

    private Map<Integer,Boolean> localOverMap = new HashMap<Integer,Boolean>();

    @Override
    public void afterPropertiesSet() throws Exception {
        //将商品数量加载到缓存中
        List<Hotels> hotelsList = hotelService.listHotels();
        List<Rooms> roomsList = roomService.listRooms();
        if (roomsList==null){
            return;
        }
        if (hotelsList==null){
            return;
        }
        for (Rooms rooms:roomsList){
            redisService.set(HotelKey.getRoomsStock,""+rooms.getRoomsId(),rooms.getCount());
            localOverMap.put(rooms.getRoomsId(),false);
        }
        for (Hotels hotels:hotelsList){
            redisService.set(HotelKey.getRoomsStock,""+hotels.getHotelId(),hotels.getTotalCount());
            localOverMap.put(hotels.getHotelId(),false);
        }
    }

    @ApiOperation(value = "用户下单", notes = "用户下单接口")
    @PostMapping(value = "/bookRoom")
    @ResponseBody
    @AccessLimit(seconds = 5,maxCount = 5,needLogin = true)
    public JSONResult bookRoom(Model model,String[] realname,String[] id_number, Users user,@RequestParam("roomId") Integer roomId,@RequestParam("verifyCode") String verifyCodeString) {
        model.addAttribute("user",user);
        //看是否登录
        System.out.println(realname);
        System.out.println(id_number);
        if(user == null || user.getUserId() == null){
            return JSONResult.errorMsg("未登录！");
//            return JSONResult.errorMap(CodeMsg.SESSION_ERROR);
        }

        //验证验证码
        Integer hotelId = roomService.getBelongHotelId(roomId);
        if(verifyCodeString == null||verifyCodeString ==""){
            return new JSONResult(505,"验证码不能为空");
        }
        int verifyCode = Integer.parseInt(verifyCodeString);

        boolean check = orderService.checkVerifyCode(user,hotelId,verifyCode);
        if(!check){
            return new JSONResult(505,"验证码错误");
        }

        //内存标记来减少redis访问，防止执行redisService.decr
        Boolean over = localOverMap.get(roomId);
        if (over){
            return JSONResult.stockend();
        }

        //预减库存
        long stock = redisService.decr(HotelKey.getRoomsStock, "" + roomId);
        if (stock<0){
            localOverMap.put(roomId,true);
            return JSONResult.stockend();
        }

        //判断是否下过单
        Orders hasorder = orderService.getOrderByUserIdRoomId(user.getUserId(),roomId);
        if(hasorder != null){
            return JSONResult.repeatOrder();
        }
        //创建住房信息
        tenantService.createTenants(realname,id_number,roomId);
        //创建订单
        JSONResult order = orderService.createOrder(user, roomId);
        return JSONResult.ok(order);
    }

    @AccessLimit(seconds = 5,maxCount = 5,needLogin = true)
    @ApiOperation(value = "获取是否下单结果", notes = "获取是否下单结果接口")
    @GetMapping(value = "/result")
    @ResponseBody
    public JSONResult bookResult(Model model, Users user,@RequestParam("roomId") Integer roomId) {
        model.addAttribute("user", user);
        //看是否登录
        if (user == null) {
            return JSONResult.errorMap(CodeMsg.SESSION_ERROR);
        }
        //判断用户有没有订购到房间，返回订单id
        Integer result = orderService.getOrderResult(user.getUserId(),roomId);
        return JSONResult.ok(result);
    }

    @AccessLimit(seconds = 5,maxCount = 5,needLogin = true)
    @ApiOperation(value = "获取订单详情", notes = "获取订单详情接口")
    @GetMapping("/detail")
    @ResponseBody
    //@NeedLogin
    //TODO 拦截器
    public JSONResult info(Model model, Users user,@RequestParam("orderId")Integer orderId) {
        if (user == null){
            return JSONResult.errorMap(CodeMsg.SESSION_ERROR);
        }
        Orders order = orderService.getOrderById(orderId);
        if(order == null){
            return JSONResult.errorMap(CodeMsg.ORDER_NOT_EXIST);
        }
        Integer roomId = order.getRoomId();
        RoomsVo room = roomService.getRoomVoByRoomId(roomId);
        OrderDetailVo vo = new OrderDetailVo();
        vo.setOrder(order);
        vo.setRoomsVo(room);
        return JSONResult.ok(vo);
    }


    @ApiOperation(value = "获取验证码", notes = "获取验证码接口")
    @RequestMapping(value = "/verifyCode",method = RequestMethod.GET)
    @ResponseBody
    @AccessLimit(seconds = 5,maxCount = 5,needLogin = true)
    public JSONResult getSeckillVerifyCode(HttpServletResponse response,Model model,@CurrentUser Users user,
                                               @RequestParam("hotelId")Integer hotelId) {
        //看是否登录
        if (user == null) {
            return JSONResult.codeMsg(CodeMsg.SESSION_ERROR);
        }
        BufferedImage image = orderService.createVerifyCode(user,hotelId);
        try{
            OutputStream out = response.getOutputStream();
            ImageIO.write(image,"JPEG",out);
            out.flush();
            out.close();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return JSONResult.errorMsg("结束");
        }
    }


    @ApiOperation(value = "我的订单页面", notes = "获取我的订单页面接口")
    @GetMapping("/myOrder")
    public String myOrder(Model model,@RequestParam Integer userId,@RequestParam(defaultValue = "1") Integer pageNum) {

        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<OrdersVo> pageInfo = orderService.queryOrderListById(userId,pageNum,PAGE_SIZE);
        model.addAttribute("pageInfo",pageInfo);

        return "myOrderList";

    }

}
