package com.example.nikhil.popularmovies.pojos.movie_details;

import com.example.nikhil.popularmovies.pojos.movie_images.BelongsToCollection;
import com.example.nikhil.popularmovies.pojos.movie_images.Images;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nikhil on 11/7/17.
 */
public class MovieDetail {
    @SerializedName("budget")
    private String budget;
    @SerializedName("vote_average")
    private String voteAverage;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("genres")
    private List<Genres> genres;
    @SerializedName("status")
    private String status;
    @SerializedName("runtime")
    private String runtime;
    @SerializedName("spoken_languages")
    private List<SpokenLanguages> spokenLanguages;
    @SerializedName("adult")
    private String adult;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("id")
    private String id;
    @SerializedName("production_countries")
    private List<ProductionCountries> productionCountries;
    @SerializedName("title")
    private String title;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("overview")
    private String overview;
    @SerializedName("production_companies")
    private List<ProductionCompanies> productionCompanies;
    @SerializedName("belongs_to_collection")
    private BelongsToCollection belongsToCollection;
    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("images")
    private Images images;
    @SerializedName("vote_count")
    private String voteCount;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("videos")
    private String video;
    @SerializedName("tagline")
    private String tagline;
    @SerializedName("revenue")
    private String revenue;
    @SerializedName("popularity")
    private String popularity;

    public String getBudget ()
    {
        return budget;
    }

    public void setBudget (String budget)
    {
        this.budget = budget;
    }

    public String getVoteAverage()
    {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage)
    {
        this.voteAverage = voteAverage;
    }

    public String getBackdropPath()
    {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath)
    {
        this.backdropPath = backdropPath;
    }

    public List<Genres> getGenres ()
    {
        return genres;
    }

    public void setGenres (List<Genres> genres)
    {
        this.genres = genres;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getRuntime ()
    {
        return runtime;
    }

    public void setRuntime (String runtime)
    {
        this.runtime = runtime;
    }

    public List<SpokenLanguages> getSpokenLanguages()
    {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguages> spokenLanguages)
    {
        this.spokenLanguages = spokenLanguages;
    }

    public String getAdult ()
    {
        return adult;
    }

    public void setAdult (String adult)
    {
        this.adult = adult;
    }

    public String getHomepage ()
    {
        return homepage;
    }

    public void setHomepage (String homepage)
    {
        this.homepage = homepage;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public List<ProductionCountries> getProductionCountries()
    {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountries> productionCountries)
    {
        this.productionCountries = productionCountries;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getOriginalLanguage()
    {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage)
    {
        this.originalLanguage = originalLanguage;
    }

    public String getOverview ()
    {
        return overview;
    }

    public void setOverview (String overview)
    {
        this.overview = overview;
    }

    public List<ProductionCompanies> getProductionCompanies()
    {
        return productionCompanies;
    }

    public void setProductionCompanies(List   <ProductionCompanies> productionCompanies)
    {
        this.productionCompanies = productionCompanies;
    }

    public BelongsToCollection getBelongsToCollection()
{
    return belongsToCollection;
}

    public void setBelongsToCollection(BelongsToCollection belongsToCollection)
    {
        this.belongsToCollection = belongsToCollection;
    }

    public String getImdbId()
    {
        return imdbId;
    }

    public void setImdbId(String imdbId)
    {
        this.imdbId = imdbId;
    }

    public String getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public String getOriginalTitle()
    {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle)
    {
        this.originalTitle = originalTitle;
    }

    public Images getImages ()
    {
        return images;
    }

    public void setImages (Images images)
    {
        this.images = images;
    }

    public String getVoteCount()
    {
        return voteCount;
    }

    public void setVoteCount(String voteCount)
    {
        this.voteCount = voteCount;
    }

    public String getPosterPath()
    {
        return posterPath;
    }

    public void setPosterPath(String posterPath)
    {
        this.posterPath = posterPath;
    }

    public String getVideo ()
    {
        return video;
    }

    public void setVideo (String video)
    {
        this.video = video;
    }

    public String getTagline ()
    {
        return tagline;
    }

    public void setTagline (String tagline)
    {
        this.tagline = tagline;
    }

    public String getRevenue ()
    {
        return revenue;
    }

    public void setRevenue (String revenue)
    {
        this.revenue = revenue;
    }

    public String getPopularity ()
    {
        return popularity;
    }

    public void setPopularity (String popularity)
    {
        this.popularity = popularity;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [budget = "+budget+", voteAverage = "+ voteAverage +", backdropPath = "+ backdropPath +", genres = "+genres+", status = "+status+", runtime = "+runtime+", spokenLanguages = "+ spokenLanguages +", adult = "+adult+", homepage = "+homepage+", id = "+id+", productionCountries = "+ productionCountries +", title = "+title+", originalLanguage = "+ originalLanguage +", overview = "+overview+", productionCompanies = "+ productionCompanies +", belongsToCollection = "+ belongsToCollection +", imdbId = "+ imdbId +", releaseDate = "+ releaseDate +", originalTitle = "+ originalTitle +", images = "+images+", voteCount = "+ voteCount +", posterPath = "+ posterPath +", video = "+video+", tagline = "+tagline+", revenue = "+revenue+", popularity = "+popularity+"]";
    }
}
