package com.example.coolnewsapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TotalHeadlines {
    @Expose
    private String status;

    @SerializedName("totalresults")
    private Integer totalresults;

    @SerializedName("articles")
    private List<NewsResult> categoriesResult;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalresults() {
        return totalresults;
    }

    public void setTotalresults(Integer totalresults) {
        this.totalresults = totalresults;
    }

    public List<NewsResult> getCategoriesResult() {
        return categoriesResult;
    }

    public void setCategoriesResult(List<NewsResult> categoriesResult) {
        this.categoriesResult = categoriesResult;
    }
}
