package com.example.nikhil.popularmovies.pojos.tv;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class TvResults implements Parcelable {

    @SerializedName("id")
    private String id;

    @SerializedName("origin_country")
    private String[] origin_country;

    @SerializedName("overview")
    private String overview;

    @SerializedName("backdrop_path")
    private String backdrop_path;

    @SerializedName("original_language")
    private String original_language;

    @SerializedName("vote_average")
    private String vote_average;

    @SerializedName("genre_ids")
    private String[] genre_ids;

    @SerializedName("name")
    private String name;

    @SerializedName("vote_count")
    private String vote_count;

    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("original_name")
    private String original_name;

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("first_air_date")
    private String first_air_date;

    public TvResults(String id, String image, String title){
        this.id = id;
        poster_path = image;
        name = title;
    }

    protected TvResults(Parcel in) {
        id = in.readString();
        origin_country = in.createStringArray();
        overview = in.readString();
        backdrop_path = in.readString();
        original_language = in.readString();
        vote_average = in.readString();
        genre_ids = in.createStringArray();
        name = in.readString();
        vote_count = in.readString();
        poster_path = in.readString();
        original_name = in.readString();
        popularity = in.readString();
        first_air_date = in.readString();
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

    public String[] getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String[] origin_country) {
        this.origin_country = origin_country;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(String[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", origin_country = " + origin_country + ", overview = " + overview + ", backdrop_path = " + backdrop_path + ", original_language = " + original_language + ", vote_average = " + vote_average + ", genre_ids = " + genre_ids + ", name = " + name + ", vote_count = " + vote_count + ", poster_path = " + poster_path + ", original_name = " + original_name + ", popularity = " + popularity + ", first_air_date = " + first_air_date + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeStringArray(origin_country);
        parcel.writeString(overview);
        parcel.writeString(backdrop_path);
        parcel.writeString(original_language);
        parcel.writeString(vote_average);
        parcel.writeStringArray(genre_ids);
        parcel.writeString(name);
        parcel.writeString(vote_count);
        parcel.writeString(poster_path);
        parcel.writeString(original_name);
        parcel.writeString(popularity);
        parcel.writeString(first_air_date);
    }
}
