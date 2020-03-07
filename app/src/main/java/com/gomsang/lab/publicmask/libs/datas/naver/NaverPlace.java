package com.gomsang.lab.publicmask.libs.datas.naver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NaverPlace {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("road_address")
    @Expose
    private String roadAddress;
    @SerializedName("jibun_address")
    @Expose
    private String jibunAddress;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("x")
    @Expose
    private String x;
    @SerializedName("y")
    @Expose
    private String y;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("sessionId")
    @Expose
    private String sessionId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoadAddress() {
        return roadAddress;
    }

    public void setRoadAddress(String roadAddress) {
        this.roadAddress = roadAddress;
    }

    public String getJibunAddress() {
        return jibunAddress;
    }

    public void setJibunAddress(String jibunAddress) {
        this.jibunAddress = jibunAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
