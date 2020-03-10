package com.zhou.controller;

import com.zhou.config.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;

public class BasicController {

    @Autowired
    private ResourceConfig resourceConfig;

    //每页分页记录数
    public static final Integer PAGE_SIZE = 6;
}
