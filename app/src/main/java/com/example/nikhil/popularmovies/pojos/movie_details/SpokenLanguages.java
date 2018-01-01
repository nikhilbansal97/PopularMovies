package com.example.nikhil.popularmovies.pojos.movie_details;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikhil on 11/7/17.
 */

public class SpokenLanguages
{
    @SerializedName("iso_639_1")
    private String iso_639_1;
    @SerializedName("name")
    private String name;

    public String getIso_639_1 ()
    {
        return iso_639_1;
    }

    public void setIso_639_1 (String iso_639_1)
    {
        this.iso_639_1 = iso_639_1;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [iso_639_1 = "+iso_639_1+", name = "+name+"]";
    }
}
