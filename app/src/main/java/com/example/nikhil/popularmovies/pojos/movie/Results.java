package com.example.nikhil.popularmovies.pojos.movie;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikhil on 6/7/17.
 */

public class Results implements Parcelable {

    @SerializedName("vote_average")
    private String voteAverage;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("adult")
    private String adult;

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("genre_ids")
    private String[] genreIds;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("vote_count")
    private String voteCount;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("video")
    private String video;

    @SerializedName("popularity")
    private String popularity;

    protected Results(Parcel in) {
        voteAverage = in.readString();
        backdropPath = in.readString();
        adult = in.readString();
        id = in.readString();
        title = in.readString();
        overview = in.readString();
        originalLanguage = in.readString();
        genreIds = in.createStringArray();
        releaseDate = in.readString();
        originalTitle = in.readString();
        voteCount = in.readString();
        posterPath = in.readString();
        video = in.readString();
        popularity = in.readString();
    }

    public static final Creator<Results> CREATOR = new Creator<Results>() {
        @Override
        public Results createFromParcel(Parcel in) {
            return new Results(in);
        }

        @Override
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String[] getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(String[] genreIds) {
        this.genreIds = genreIds;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "ClassPojo [voteAverage = " + voteAverage + ", backdropPath = " + backdropPath + ", adult = " + adult + ", id = " + id + ", title = " + title + ", overview = " + overview + ", originalLanguage = " + originalLanguage + ", genreIds = " + genreIds + ", releaseDate = " + releaseDate + ", originalTitle = " + originalTitle + ", voteCount = " + voteCount + ", posterPath = " + posterPath + ", video = " + video + ", popularity = " + popularity + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(voteAverage);
        parcel.writeString(backdropPath);
        parcel.writeString(adult);
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(overview);
        parcel.writeString(originalLanguage);
        parcel.writeStringArray(genreIds);
        parcel.writeString(releaseDate);
        parcel.writeString(originalTitle);
        parcel.writeString(voteCount);
        parcel.writeString(posterPath);
        parcel.writeString(video);
        parcel.writeString(popularity);
    }
}