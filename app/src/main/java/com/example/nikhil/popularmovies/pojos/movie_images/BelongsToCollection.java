package com.example.nikhil.popularmovies.pojos.movie_images;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikhil on 11/7/17.
 */

public class BelongsToCollection
{
    @SerializedName("id")
    private String id;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("name")
    private String name;
    @SerializedName("poster_path")
    private String posterPath;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getBackdropPath()
    {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath)
    {
        this.backdropPath = backdropPath;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getPosterPath()
    {
        return posterPath;
    }

    public void setPosterPath(String posterPath)
    {
        this.posterPath = posterPath;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", backdropPath = "+ backdropPath +", name = "+name+", posterPath = "+ posterPath +"]";
    }
}