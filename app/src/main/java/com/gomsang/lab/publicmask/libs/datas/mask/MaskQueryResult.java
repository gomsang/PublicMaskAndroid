package com.gomsang.lab.publicmask.libs.datas.mask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class MaskQueryResult {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("stores")
    @Expose
    private List<Store> stores = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
}