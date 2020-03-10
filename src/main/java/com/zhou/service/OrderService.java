package com.zhou.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhou.mapper.OrdersMapper;
import com.zhou.mapper.OrdersMapperCustom;
import com.zhou.pojo.Orders;
import com.zhou.pojo.Users;
import com.zhou.redis.HotelKey;
import com.zhou.redis.OrderKey;
import com.zhou.redis.RedisService;
import com.zhou.redis.RoomKey;
import com.zhou.utils.JSONResult;
import com.zhou.vo.OrderDetailVo;
import com.zhou.vo.OrdersVo;
import com.zhou.vo.RoomsVo;
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
public class OrderService {
    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private OrdersMapperCustom ordersMapperCustom;

    @Transactional(propagation = Propagation.REQUIRED)
    public JSONResult createOrder(Users user, Integer roomId) {
        Orders order = new Orders();
        order.setCreateTime(new Date());
        Integer hotelId = roomService.getBelongHotelId(roomId);
        order.setHotelId(hotelId);
        order.setPayment(roomService.getPriceByRoomId(roomId).intValue());
        //TODO:数据库中订单表和房型表的价格数据类型不一致
        order.setRoomId(roomId);
        order.setState("未付");
        order.setUserId(user.getUserId());
        ordersMapper.insert(order);
        roomService.descCount(roomId);
        hotelService.descTotalCount(hotelId);

        //生成订单后写入缓存
//        redisService.set(HotelKey.getOrderByUidRid,""+user.getUserId()+"_"+roomId,order);

        return JSONResult.ok(order);
    }


    //一个用户只能定一种类型房间一次
    //使用用户号和房间号获取订单id
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer getOrderResult(Integer userId, Integer roomId) {
        Orders order = getOrderByUserIdRoomId(userId, roomId);
        //秒杀成功，返回订单id

        if (order != null){
            return order.getOrderId();
        }else{
            boolean isOver = getRoomOver(roomId);
            //真正卖完了
            if (isOver){
                return -1;
            }else{
                return 0;
            }
        }
    }


    public Orders getOrderByUserIdRoomId(Integer userId, Integer roomId) {
        //做优化，查缓存
//        Orders order =  redisService.get(HotelKey.getOrderByUidRid,""+userId+"_"+roomId,Orders.class);
//        if (order != null){
//            return order;
//        }
        Orders order = new Orders();
        order.setRoomId(roomId);
        order.setUserId(userId);
        Orders result = ordersMapper.selectOne(order);
        return result;

    }

    private void setRoomOver(Integer roomId) {
        redisService.set(RoomKey.isRoomOver,""+roomId,true);
    }

    private boolean getRoomOver(Integer roomId) {
        return redisService.exists(RoomKey.isRoomOver,""+roomId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Orders getOrderById(Integer orderId) {
        Orders order = new Orders();
        order.setOrderId(orderId);
        return ordersMapper.selectOne(order);
    }


    public BufferedImage createVerifyCode(Users user, Integer hotelId) {
        if (user == null||hotelId<=0){
            return null;
        }
        int width = 80;
        int height = 32;
        //create the image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // set the background color
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);
        // draw the border
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        // create a random instance to generate the codes
        Random rdm = new Random();
        // make some confusion
        for (int i = 0; i < 50; i++) {
            int x = rdm.nextInt(width);
            int y = rdm.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }
        // generate a random code
        String verifyCode = generateVerifyCode(rdm);
        g.setColor(new Color(0, 100, 0));
        g.setFont(new Font("Candara", Font.BOLD, 24));
        g.drawString(verifyCode, 8, 24);
        g.dispose();
        //把验证码存到redis中
        int rnd = calc(verifyCode);
        redisService.set(OrderKey.getVerifyCode, user.getUserId()+","+hotelId, rnd);
        //输出图片
        return image;
    }

    private static char[] ops = new char[]{'+','-','*'};
    //只做加减乘
    private String generateVerifyCode(Random rdm) {
        int num1 = rdm.nextInt(10);
        int num2 = rdm.nextInt(10);
        int num3 = rdm.nextInt(10);
        char op1 = ops[rdm.nextInt(3)];
        char op2 = ops[rdm.nextInt(3)];
        String exp =""+ num1 + op1 + num2 +op2 + num3;
        return exp;
    }

    private static int calc(String exp) {
        try{
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            return (Integer) engine.eval(exp);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public boolean checkVerifyCode(Users user, Integer hotelId, int verifyCode) {
        if (user == null||hotelId<=0){
            return false;
        }
        Integer codeOld = redisService.get(OrderKey.getVerifyCode, user.getUserId() + "," + hotelId, Integer.class);
        if (codeOld == null||codeOld-verifyCode!=0){
            return false;
        }
        redisService.delete(OrderKey.getVerifyCode, user.getUserId() + "," + hotelId);
        return true;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PageInfo<OrdersVo> queryOrderListById(Integer userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);


        List<OrdersVo> list = ordersMapperCustom.queryMyOrders(userId);

//        Orders order = new Orders();
//        order.setUserId(userId);
//        List<Orders> list = ordersMapper.select(order);
        PageInfo<OrdersVo> pageList = new PageInfo<>(list);
        return pageList;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PageInfo<OrdersVo> getAllOrdersVo(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<OrdersVo> list = ordersMapperCustom.selectAllOrdersList();
        PageInfo<OrdersVo> pageList = new PageInfo<>(list);
        return pageList;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PageInfo<Orders> getAllOrders(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Orders> list = ordersMapper.selectAll();
        PageInfo<Orders> pageList = new PageInfo<>(list);
        return pageList;
    }
}
