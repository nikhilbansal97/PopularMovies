package com.example.nikhil.popularmovies.pojos.movie_details;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikhil on 11/7/17.
 */

public class ProductionCountries
{
    @SerializedName("name")
    private String name;
    @SerializedName("iso_3166_1")
    private String iso_3166_1;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getIso_3166_1 ()
    {
        return iso_3166_1;
    }

    public void setIso_3166_1 (String iso_3166_1)
    {
        this.iso_3166_1 = iso_3166_1;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", iso_3166_1 = "+iso_3166_1+"]";
    }
}