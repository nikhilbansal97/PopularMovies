package com.example.nikhil.popularmovies.pojos.movie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nikhil on 6/7/17.
 */

public class Response {

    @SerializedName("results")
    private List<Results> results;

    @SerializedName("page")
    private String page;

    @SerializedName("total_pages")
    private String total_pages;

    @SerializedName("total_results")
    private String total_results;

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
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