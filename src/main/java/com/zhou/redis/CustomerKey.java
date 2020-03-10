package com.zhou.redis;

public class CustomerKey extends BasePrefix {
    public static final int TOKEN_EXPIRE = 3600*24*2;//两天
    public CustomerKey(int expireSeconds, String prefix) {
        super(expireSeconds,prefix);
    }

    public static CustomerKey token = new CustomerKey(TOKEN_EXPIRE,"tk");
    public static CustomerKey getById = new CustomerKey(0,"id");

}
