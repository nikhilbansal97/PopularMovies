package com.example.nikhil.popularmovies.pojos.movie_images;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nikhil on 11/7/17.
 */
public class Images
{
    @SerializedName("backdrops")
    private List<Backdrops> backdrops;
    @SerializedName("posters")
    private List<Posters> posters;

    public List<Backdrops> getBackdrops ()
    {
        return backdrops;
    }

    public void setBackdrops (List<Backdrops> backdrops)
    {
        this.backdrops = backdrops;
    }

    public List<Posters> getPosters ()
    {
        return posters;
    }

    public void setPosters (List<Posters> posters)
    {
        this.posters = posters;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [backdrops = "+backdrops+", posters = "+posters+"]";
    }
}