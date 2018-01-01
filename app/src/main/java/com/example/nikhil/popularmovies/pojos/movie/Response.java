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
    private String totalPages;

    @SerializedName("total_results")
    private String totalResults;

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