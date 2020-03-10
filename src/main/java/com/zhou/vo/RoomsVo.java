package com.zhou.vo;

import lombok.Data;

@Data
public class RoomsVo {
    private Integer roomsId;

    private Integer hotelId;

    private Float price;

    private String type;

    private Float size;

    private String facility;

    private Integer count;

    private String hotelName;
}