package com.example.nikhil.popularmovies.pojos.tv;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularTv {

    @SerializedName("results")
    private List<TvResults> results;

    @SerializedName("page")
    private String page;

    @SerializedName("total_pages")
    private String total_pages;

    @SerializedName("total_results")
    private String total_results;

    public List<TvResults> getResults() {
        return results;
    }

    public void setResults(List<TvResults> results) {
        this.results = results;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public String getTotal_results() {
        return total_results;
    }

    public void setTotal_results(String total_results) {
        this.total_results = total_results;
    }

    @Override
    public String toString() {
        return "ClassPojo [results = " + results + ", page = " + page + ", total_pages = " + total_pages + ", total_results = " + total_results + "]";
    }
}