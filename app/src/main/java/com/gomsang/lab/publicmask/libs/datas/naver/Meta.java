package com.gomsang.lab.publicmask.libs.datas.naver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta {
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("count")
    @Expose
    private Integer count;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}