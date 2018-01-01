package com.example.nikhil.popularmovies.pojos.movie_images;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikhil on 11/7/17.
 */

public class Backdrops {

    @SerializedName("iso_639_1")
    private String iso_639_1;
    @SerializedName("height")
    private String height;
    @SerializedName("vote_average")
    private String voteAverage;
    @SerializedName("file_path")
    private String filePath;
    @SerializedName("width")
    private String width;
    @SerializedName("vote_count")
    private String voteCount;
    @SerializedName("aspect_ratio")
    private String aspectRatio;

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public String getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    @Override
    public String toString() {
        return "ClassPojo [iso_639_1 = " + iso_639_1 + ", height = " + height + ", voteAverage = " + voteAverage + ", filePath = " + filePath + ", width = " + width + ", voteCount = " + voteCount + ", aspectRatio = " + aspectRatio + "]";
    }
}
