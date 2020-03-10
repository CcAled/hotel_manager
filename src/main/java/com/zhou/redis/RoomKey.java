package com.zhou.redis;

public class RoomKey extends BasePrefix{


    private RoomKey(int expireSeconds, String prefix) {
        super(expireSeconds,prefix);
    }

    public static RoomKey getRoomsList = new RoomKey(0,"rl");
    public static RoomKey isRoomOver = new RoomKey(0,"go");
    public static RoomKey getBookPath = new RoomKey(60,"mp");
    public static RoomKey getBookVerifyCode = new RoomKey(300,"vc");

}
