package com.zhou.redis;

public class HotelKey extends BasePrefix{


    private HotelKey(int expireSeconds, String prefix) {
        super(expireSeconds,prefix);
    }

    public static HotelKey getOrderByUidRid = new HotelKey(0,"oBur");

    public static HotelKey getHotelsList = new HotelKey(0,"hl");

    public static HotelKey searchHotelList = new HotelKey(0,"sl");

    public static HotelKey getRoomsStock = new HotelKey(0,"rs");

}
