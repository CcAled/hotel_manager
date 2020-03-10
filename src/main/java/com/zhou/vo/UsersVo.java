package com.zhou.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UsersVo {
    private Integer userId;

    private String username;

    private String password;

    private String salt;

    private String email;

    private String phone;

    private String realName;

    private Date birthday;

    private String certificateType;

    private String certificateNumber;

    private String userToken;

    public UsersVo() {
    }

    public UsersVo(Integer userId, String username, String password, String salt, String email, String phone, String realName, Date birthday, String certificateType, String certificateNumber, String userToken) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.email = email;
        this.phone = phone;
        this.realName = realName;
        this.birthday = birthday;
        this.certificateType = certificateType;
        this.certificateNumber = certificateNumber;
        this.userToken = userToken;
    }

    public void setUserToken(String uniqueToken) {
    }
}
