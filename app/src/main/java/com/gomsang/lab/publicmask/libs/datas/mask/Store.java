package com.gomsang.lab.publicmask.libs.datas.mask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Store {

    @SerializedName("addr")
    @Expose
    private String addr;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("remain_cnt")
    @Expose
    private Integer remainCnt;
    @SerializedName("sold_cnt")
    @Expose
    private Integer soldCnt;
    @SerializedName("sold_out")
    @Expose
    private Boolean soldOut;
    @SerializedName("stock_cnt")
    @Expose
    private Integer stockCnt;
    @SerializedName("stock_t")
    @Expose
    private String stockT;
    @SerializedName("type")
    @Expose
    private String type;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRemainCnt() {
        return remainCnt;
    }

    public void setRemainCnt(Integer remainCnt) {
        this.remainCnt = remainCnt;
    }

    public Integer getSoldCnt() {
        return soldCnt;
    }

    public void setSoldCnt(Integer soldCnt) {
        this.soldCnt = soldCnt;
    }

    public Boolean getSoldOut() {
        return soldOut;
    }

    public void setSoldOut(Boolean soldOut) {
        this.soldOut = soldOut;
    }

    public Integer getStockCnt() {
        return stockCnt;
    }

    public void setStockCnt(Integer stockCnt) {
        this.stockCnt = stockCnt;
    }

    public String getStockT() {
        return stockT;
    }

    public void setStockT(String stockT) {
        this.stockT = stockT;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}