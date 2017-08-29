package com.example.nikhil.popularmovies.pojos.movie_details;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nikhil on 31/7/17.
 */

public class Credits {

    @SerializedName("cast")
    private List<Cast> cast;

    @SerializedName("crew")
    private List<Crew> crew;

    @SerializedName("id")
    private String id;

    public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }

    public List<Cast> getCast () {
        return cast;
    }

    public void setCast (List<Cast> cast) {
        this.cast = cast;
    }

    public List<Crew> getCrew () {
        return crew;
    }

    public void setCrew (List<Crew> crew) {
        this.crew = crew;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", cast = "+cast+", crew = "+crew+"]";
    }
}
