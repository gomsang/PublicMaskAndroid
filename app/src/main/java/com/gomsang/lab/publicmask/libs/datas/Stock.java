package com.gomsang.lab.publicmask.libs.datas;

import com.gomsang.lab.publicmask.libs.datas.mask.Store;

public class Stock {
    private String dealerName;
    private String dealerAddress;
    private Double dealerLatitude;
    private Double dealerLongitude;

    private String updatedTime;

    private String stockTime;
    private int stockTotalCount;
    private int soldCount;
    private int remainCount;

    private boolean isClosed;

    public static Stock convert(Store store) {
        Stock stock = new Stock();
        stock.dealerName = store.getName();
        stock.dealerAddress = store.getAddr();
        stock.dealerLatitude = store.getLat();
        stock.dealerLongitude = store.getLng();
        stock.updatedTime = store.getCreatedAt();
        stock.stockTime = store.getStockT();
        stock.stockTotalCount = store.getStockCnt();
        stock.soldCount = store.getSoldCnt();
        stock.remainCount = store.getRemainCnt();
        stock.isClosed = store.getSoldOut();
        return stock;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealerAddress() {
        return dealerAddress;
    }

    public void setDealerAddress(String dealerAddress) {
        this.dealerAddress = dealerAddress;
    }

    public Double getDealerLatitude() {
        return dealerLatitude;
    }

    public void setDealerLatitude(Double dealerLatitude) {
        this.dealerLatitude = dealerLatitude;
    }

    public Double getDealerLongitude() {
        return dealerLongitude;
    }

    public void setDealerLongitude(Double dealerLongitude) {
        this.dealerLongitude = dealerLongitude;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getStockTime() {
        return stockTime;
    }

    public void setStockTime(String stockTime) {
        this.stockTime = stockTime;
    }

    public int getStockTotalCount() {
        return stockTotalCount;
    }

    public void setStockTotalCount(int stockTotalCount) {
        this.stockTotalCount = stockTotalCount;
    }

    public int getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(int soldCount) {
        this.soldCount = soldCount;
    }

    public int getRemainCount() {
        return remainCount;
    }

    public void setRemainCount(int remainCount) {
        this.remainCount = remainCount;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }
}
