package com.zhou.utils;

import org.springframework.util.DigestUtils;

public class MD5Util {

    public static String md5(String src){
        return DigestUtils.md5DigestAsHex(src.getBytes());
    }

    public static final String salt = "abcdefgh";

    public static String inputPassToFormPass(String inputPass){
        String str = ""+salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass,String salt){
        String str = ""+salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDbPass(String input,String saltDB){
        String formPass = inputPassToFormPass(input);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }
    public static void main(String[] args) {
        System.out.println(inputPassToFormPass("123456"));
        System.out.println(formPassToDBPass(inputPassToFormPass("123456"),"abcdefgh"));
        System.out.println(inputPassToDbPass("123456","abcdefgh"));
    }
}
