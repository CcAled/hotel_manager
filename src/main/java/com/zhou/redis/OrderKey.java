package com.zhou.redis;

public class OrderKey extends BasePrefix{


    private OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds,prefix);
    }

    public static OrderKey getVerifyCode = new OrderKey(0,"vc");

}
