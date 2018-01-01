package com.example.nikhil.popularmovies.pojos.movie_details;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikhil on 31/7/17.
 */

public class Crew {
    @SerializedName("id")
    private String id;

    @SerializedName("credit_id")
    private String creditId;

    @SerializedName("department")
    private String department;

    @SerializedName("name")
    private String name;

    @SerializedName("job")
    private String job;

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

    public String getCreditId()
    {
        return creditId;
    }

    public void setCreditId(String creditId)
    {
        this.creditId = creditId;
    }

    public String getDepartment ()
    {
        return department;
    }

    public void setDepartment (String department)
    {
        this.department = department;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getJob ()
    {
        return job;
    }

    public void setJob (String job)
    {
        this.job = job;
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
        return "ClassPojo [id = "+id+", creditId = "+ creditId +", department = "+department+", name = "+name+", job = "+job+", gender = "+gender+", profilePath = "+ profilePath +"]";
    }
}
