package com.zhou.vo;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class OrdersVo {
    @Id
    private Integer orderId;

    private Integer userId;

    private Integer roomId;

    private Integer hotelId;

    private String hotelName;

    private String state;

    private Date createTime;

    private Integer payment;

}