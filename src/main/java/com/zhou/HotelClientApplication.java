package com.zhou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages="com.zhou.mapper")

public class HotelClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelClientApplication.class, args);
    }

}
