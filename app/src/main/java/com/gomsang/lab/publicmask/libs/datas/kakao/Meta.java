package com.gomsang.lab.publicmask.libs.datas.kakao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Meta {

    @SerializedName("is_end")
    @Expose
    private Boolean isEnd;
    @SerializedName("pageable_count")
    @Expose
    private Integer pageableCount;
    @SerializedName("same_name")
    @Expose
    private SameName sameName;
    @SerializedName("total_count")
    @Expose
    private Integer totalCount;

    public Boolean getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Boolean isEnd) {
        this.isEnd = isEnd;
    }

    public Integer getPageableCount() {
        return pageableCount;
    }

    public void setPageableCount(Integer pageableCount) {
        this.pageableCount = pageableCount;
    }

    public SameName getSameName() {
        return sameName;
    }

    public void setSameName(SameName sameName) {
        this.sameName = sameName;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public class SameName {

        @SerializedName("keyword")
        @Expose
        private String keyword;
        @SerializedName("region")
        @Expose
        private List<Object> region = null;
        @SerializedName("selected_region")
        @Expose
        private String selectedRegion;

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public List<Object> getRegion() {
            return region;
        }

        public void setRegion(List<Object> region) {
            this.region = region;
        }

        public String getSelectedRegion() {
            return selectedRegion;
        }

        public void setSelectedRegion(String selectedRegion) {
            this.selectedRegion = selectedRegion;
        }

    }
}