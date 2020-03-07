package com.gomsang.lab.publicmask.libs.datas.naver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NaverPlaceSearchResult {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("places")
    @Expose
    private List<NaverPlace> places = null;
    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<NaverPlace> getNaverPlaces() {
        return places;
    }

    public void setNaverPlaces(List<NaverPlace> places) {
        this.places = places;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
