package com.example.nikhil.popularmovies.pojos.tv;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class TvResults implements Parcelable {

    @SerializedName("id")
    private String id;

    @SerializedName("origin_country")
    private String[] originCountry;

    @SerializedName("overview")
    private String overview;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("vote_average")
    private String voteAverage;

    @SerializedName("genre_ids")
    private String[] genreIds;

    @SerializedName("name")
    private String name;

    @SerializedName("vote_count")
    private String voteCount;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("original_name")
    private String originalName;

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("first_air_date")
    private String firstAirDate;

    public TvResults(String id, String image, String title){
        this.id = id;
        posterPath = image;
        name = title;
    }

    protected TvResults(Parcel in) {
        id = in.readString();
        originCountry = in.createStringArray();
        overview = in.readString();
        backdropPath = in.readString();
        originalLanguage = in.readString();
        voteAverage = in.readString();
        genreIds = in.createStringArray();
        name = in.readString();
        voteCount = in.readString();
        posterPath = in.readString();
        originalName = in.readString();
        popularity = in.readString();
        firstAirDate = in.readString();
    }

    public static final Creator<TvResults> CREATOR = new Creator<TvResults>() {
        @Override
        public TvResults createFromParcel(Parcel in) {
            return new TvResults(in);
        }

        @Override
        public TvResults[] newArray(int size) {
            return new TvResults[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String[] originCountry) {
        this.originCountry = originCountry;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String[] getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(String[] genreIds) {
        this.genreIds = genreIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", originCountry = " + originCountry + ", overview = " + overview + ", backdropPath = " + backdropPath + ", originalLanguage = " + originalLanguage + ", voteAverage = " + voteAverage + ", genreIds = " + genreIds + ", name = " + name + ", voteCount = " + voteCount + ", posterPath = " + posterPath + ", originalName = " + originalName + ", popularity = " + popularity + ", firstAirDate = " + firstAirDate + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeStringArray(originCountry);
        parcel.writeString(overview);
        parcel.writeString(backdropPath);
        parcel.writeString(originalLanguage);
        parcel.writeString(voteAverage);
        parcel.writeStringArray(genreIds);
        parcel.writeString(name);
        parcel.writeString(voteCount);
        parcel.writeString(posterPath);
        parcel.writeString(originalName);
        parcel.writeString(popularity);
        parcel.writeString(firstAirDate);
    }
}
