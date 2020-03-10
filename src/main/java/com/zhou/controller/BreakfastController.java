package com.zhou.controller;

import com.zhou.pojo.Breakfast;
import com.zhou.pojo.Hotels;
import com.zhou.service.BreakfastService;
import com.zhou.utils.JSONResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BreakfastController {

    @Autowired
    private BreakfastService breakfastService;

    @ApiOperation(value = "早餐列表页面", notes = "获取早餐列表页面接口")
    @GetMapping("/breakfast")
    public String queryBreakfastList(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam Integer hotelId, @RequestParam(defaultValue = "1") Integer pageNum) {
        //取缓存

        if (pageNum == null) {
            pageNum = 1;
        }



        return "breakfast";

    }

    @ApiOperation(value = "早餐详情页面", notes = "获取早餐详情页面接口")
    @GetMapping(value = "/breakfastdetail/{hotelId}")
    @ResponseBody
    public JSONResult detail(HttpServletRequest request, HttpServletResponse response,
                             Model model, @PathVariable("hotelId") Integer hotelId) {
        Breakfast breakfast=breakfastService.findBreakfastByhotelId(hotelId);
        model.addAttribute("breakfast", breakfast);
        return JSONResult.ok(breakfast);
    }

}
