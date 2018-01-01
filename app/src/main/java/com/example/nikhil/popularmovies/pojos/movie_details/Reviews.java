package com.example.nikhil.popularmovies.pojos.movie_details;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikhil on 11/9/17.
 */

public class Reviews {

    @SerializedName("id")
    private String id;
    @SerializedName("results")
    private ReviewResults[] results;
    @SerializedName("page")
    private String page;
    @SerializedName("total_pages")
    private String totalPages;
    @SerializedName("total_Results")
    private String totalResults;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public ReviewResults[] getResults ()
    {
        return results;
    }

    public void setResults (ReviewResults[] results)
    {
        this.results = results;
    }

    public String getPage ()
    {
        return page;
    }

    public void setPage (String page)
    {
        this.page = page;
    }

    public String getTotalPages()
    {
        return totalPages;
    }

    public void setTotalPages(String totalPages)
    {
        this.totalPages = totalPages;
    }

    public String getTotalResults()
    {
        return totalResults;
    }

    public void setTotalResults(String totalResults)
    {
        this.totalResults = totalResults;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", results = "+results+", page = "+page+", totalPages = "+ totalPages +", totalResults = "+ totalResults +"]";
    }
}
