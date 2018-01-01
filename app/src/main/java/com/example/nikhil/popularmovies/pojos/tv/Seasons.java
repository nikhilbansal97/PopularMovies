package com.example.nikhil.popularmovies.pojos.tv;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikhil on 24/8/17.
 */

public class Seasons {

    @SerializedName("id")
    private String id;

    @SerializedName("season_number")
    private String seasonNumber;

    @SerializedName("episode_count")
    private String episodeCount;

    @SerializedName("air_date")
    private String airDate;

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

    public String getSeasonNumber()
    {
        return seasonNumber;
    }

    public void setSeasonNumber(String seasonNumber)
    {
        this.seasonNumber = seasonNumber;
    }

    public String getEpisodeCount()
    {
        return episodeCount;
    }

    public void setEpisodeCount(String episodeCount)
    {
        this.episodeCount = episodeCount;
    }

    public String getAirDate()
    {
        return airDate;
    }

    public void setAirDate(String airDate)
    {
        this.airDate = airDate;
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
        return "ClassPojo [id = "+id+", seasonNumber = "+ seasonNumber +", episodeCount = "+ episodeCount +", airDate = "+ airDate +", posterPath = "+ posterPath +"]";
    }
}
