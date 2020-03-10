package com.zhou.interceptor;


import com.zhou.pojo.Users;

public class UserContext {
    private static ThreadLocal<Users> userHolder = new ThreadLocal<Users>();

    public static void setUser(Users user){
        userHolder.set(user);
    }

    public static Users getUser(){
        return userHolder.get();
    }
}
