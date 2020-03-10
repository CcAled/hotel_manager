package com.zhou.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import java.util.Date;

@ApiModel(value = "用户对象",description = "用户对象")
public class Users {
    @Id
    @ApiModelProperty(hidden = true)
    private Integer userId;

    @ApiModelProperty(value = "用户名",name = "username",example = "zhou",required = true)
    private String username;

    @ApiModelProperty(value = "密码",name = "password",example = "zhou",required = true)
    private String password;

    @ApiModelProperty(hidden = true)
    private String salt;

    @ApiModelProperty(hidden = true)
    private String email;

    @ApiModelProperty(hidden = true)
    private String phone;

    @ApiModelProperty(hidden = true)
    private String realName;

    @ApiModelProperty(hidden = true)
    private Date birthday;

    @ApiModelProperty(hidden = true)
    private String certificateType;

    @ApiModelProperty(hidden = true)
    private String certificateNumber;

    @ApiModelProperty(hidden = true)
    private String sex;

    @ApiModelProperty(hidden = true)
    private String pwQuestion;

    @ApiModelProperty(hidden = true)
    private String pwAnswer;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType == null ? null : certificateType.trim();
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber == null ? null : certificateNumber.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPwQuestion() {
        return pwQuestion;
    }

    public void setPwQuestion(String pwQuestion) {
        this.pwQuestion = pwQuestion == null ? null : pwQuestion.trim();
    }

    public String getPwAnswer() {
        return pwAnswer;
    }

    public void setPwAnswer(String pwAnswer) {
        this.pwAnswer = pwAnswer == null ? null : pwAnswer.trim();
    }
}