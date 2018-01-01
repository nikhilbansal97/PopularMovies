package com.example.nikhil.popularmovies.pojos.tv;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularTv {

    @SerializedName("results")
    private List<TvResults> results;

    @SerializedName("page")
    private String page;

    @SerializedName("total_pages")
    private String totalPages;

    @SerializedName("total_results")
    private String totalResults;

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

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    @Override
    public String toString() {
        return "ClassPojo [results = " + results + ", page = " + page + ", totalPages = " + totalPages + ", totalResults = " + totalResults + "]";
    }
}