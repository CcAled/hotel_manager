package com.zhou.pojo;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;

public class Hotels {
    @Id
    @ApiModelProperty(hidden = true)
    private Integer hotelId;

    @ApiModelProperty(value = "酒店名",name = "hotelName",example = "xilaideng",required = true)
    private String hotelName;

    @ApiModelProperty(hidden = true)
    private String imgUrl;

    @ApiModelProperty(value = "星级",name = "starRate",example = "wuxingji",required = true)
    private String starRate;

    @ApiModelProperty(value = "酒店地址",name = "address",example = "chengdu",required = true)
    private String address;

    @ApiModelProperty(value = "附近的地标景点",name = "sight",example = "guanyinqiao",required = true)
    private String sight;

    @ApiModelProperty(value = "酒店设施",name = "facility",example = "yongchi",required = true)
    private String facility;

    @ApiModelProperty(hidden = true)
    private String breakfast;

    @ApiModelProperty(hidden = true)
    private String description;

    @ApiModelProperty(hidden = true)
    private Integer totalCount;

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName == null ? null : hotelName.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getStarRate() {
        return starRate;
    }

    public void setStarRate(String starRate) {
        this.starRate = starRate == null ? null : starRate.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getSight() {
        return sight;
    }

    public void setSight(String sight) {
        this.sight = sight == null ? null : sight.trim();
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility == null ? null : facility.trim();
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast == null ? null : breakfast.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}