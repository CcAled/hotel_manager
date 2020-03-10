package com.zhou.controller;

import com.zhou.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@Api(value = "地图业务接口",tags = "地图业务Contrller")
public class MapController extends BasicController{

    @Autowired
    private TestService testService;

    @ApiOperation(value = "地图页面\", notes = \"请求地图页面口")
    @GetMapping("/Bmap_1")
    public String toMap() {
        return "Bmap_1";
    }
}
