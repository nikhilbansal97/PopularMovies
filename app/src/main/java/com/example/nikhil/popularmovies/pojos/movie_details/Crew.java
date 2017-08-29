package com.example.nikhil.popularmovies.pojos.movie_details;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikhil on 31/7/17.
 */

public class Crew {
    @SerializedName("id")
    private String id;

    @SerializedName("credit_id")
    private String credit_id;

    @SerializedName("department")
    private String department;

    @SerializedName("name")
    private String name;

    @SerializedName("job")
    private String job;

    @SerializedName("gender")
    private String gender;

    @SerializedName("profile_path")
    private String profile_path;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getCredit_id ()
    {
        return credit_id;
    }

    public void setCredit_id (String credit_id)
    {
        this.credit_id = credit_id;
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

    public String getProfile_path ()
    {
        return profile_path;
    }

    public void setProfile_path (String profile_path)
    {
        this.profile_path = profile_path;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", credit_id = "+credit_id+", department = "+department+", name = "+name+", job = "+job+", gender = "+gender+", profile_path = "+profile_path+"]";
    }
}
