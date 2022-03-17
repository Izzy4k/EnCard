package com.example.encard.domain.model.video.entity;

import com.example.encard.domain.model.video.entity.HitVideo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PixaBoyVideo {
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("totalHits")
    @Expose
    private Integer totalHits;
    @SerializedName("hits")
    @Expose
    private List<HitVideo> hits = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(Integer totalHits) {
        this.totalHits = totalHits;
    }

    public List<HitVideo> getHits() {
        return hits;
    }

    public void setHits(List<HitVideo> hits) {
        this.hits = hits;
    }
}
