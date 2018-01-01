package com.example.nikhil.popularmovies.pojos.movie_details;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikhil on 31/7/17.
 */

public class Cast {
    @SerializedName("id")
    private String id;

    @SerializedName("order")
    private String order;

    @SerializedName("credit_id")
    private String creditId;

    @SerializedName("name")
    private String name;

    @SerializedName("gender")
    private String gender;

    @SerializedName("cast_id")
    private String castId;

    @SerializedName("profile_path")
    private String profilePath;

    @SerializedName("character")
    private String character;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getOrder ()
    {
        return order;
    }

    public void setOrder (String order)
    {
        this.order = order;
    }

    public String getCreditId()
    {
        return creditId;
    }

    public void setCreditId(String creditId)
    {
        this.creditId = creditId;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getGender ()
    {
        return gender;
    }

    public void setGender (String gender)
    {
        this.gender = gender;
    }

    public String getCastId()
    {
        return castId;
    }

    public void setCastId(String castId)
    {
        this.castId = castId;
    }

    public String getProfilePath()
    {
        return profilePath;
    }

    public void setProfilePath(String profilePath)
    {
        this.profilePath = profilePath;
    }

    public String getCharacter ()
    {
        return character;
    }

    public void setCharacter (String character)
    {
        this.character = character;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", order = "+order+", creditId = "+ creditId +", name = "+name+", gender = "+gender+", castId = "+ castId +", profilePath = "+ profilePath +", character = "+character+"]";
    }
}