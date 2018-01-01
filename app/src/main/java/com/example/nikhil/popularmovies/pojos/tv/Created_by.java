package com.example.nikhil.popularmovies.pojos.tv;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikhil on 24/8/17.
 */

public class Created_by {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("gender")
    private String gender;

    @SerializedName("profile_path")
    private String profilePath;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
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

    public String getProfilePath()
    {
        return profilePath;
    }

    public void setProfilePath(String profilePath)
    {
        this.profilePath = profilePath;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", name = "+name+", gender = "+gender+", profilePath = "+ profilePath +"]";
    }
}