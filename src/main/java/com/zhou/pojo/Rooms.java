package com.zhou.pojo;

import javax.persistence.Id;

public class Rooms {

    @Id
    private Integer roomsId;

    private Integer hotelId;

    private Float price;

    private String type;

    private Float size;

    private String facility;

    private Integer count;

    public Integer getRoomsId() {
        return roomsId;
    }

    public void setRoomsId(Integer roomsId) {
        this.roomsId = roomsId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility == null ? null : facility.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}