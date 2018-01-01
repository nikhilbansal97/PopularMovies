package com.example.nikhil.popularmovies.pojos.tv;

import com.example.nikhil.popularmovies.pojos.movie_details.Genres;
import com.example.nikhil.popularmovies.pojos.movie_details.ProductionCompanies;
import com.example.nikhil.popularmovies.pojos.movie_images.Images;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nikhil on 24/8/17.
 */

public class TvDetails {

    @SerializedName("vote_average")
    private String voteAverage;

    @SerializedName("genres")
    private List<Genres> genres;

    @SerializedName("episode_run_time")
    private String[] episodeRunTime;

    @SerializedName("type")
    private String type;

    @SerializedName("id")
    private String id;

    @SerializedName("languages")
    private String[] languages;

    @SerializedName("number_of_seasons")
    private String numberOfSeasons;

    @SerializedName("last_air_date")
    private String lastAirDate;

    @SerializedName("in_production")
    private String inProduction;

    @SerializedName("name")
    private String name;

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("networks")
    private List<Networks> networks;

    @SerializedName("created_by")
    private Created_by[] created_by;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("status")
    private String status;

    @SerializedName("number_of_episodes")
    private String numberOfEpisodes;

    @SerializedName("original_name")
    private String originalName;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("origin_country")
    private String[] originCountry;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("overview")
    private String overview;

    @SerializedName("production_companies")
    private List<ProductionCompanies> productionCompanies;

    @SerializedName("seasons")
    private List<Seasons> seasons;

    @SerializedName("images")
    private Images images;

    @SerializedName("vote_count")
    private String voteCount;

    @SerializedName("poster_path")
    private String posterPath;

    public String getVoteAverage()
    {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage)
    {
        this.voteAverage = voteAverage;
    }

    public List<Genres> getGenres ()
    {
        return genres;
    }

    public void setGenres (List<Genres> genres)
    {
        this.genres = genres;
    }

    public String[] getEpisodeRunTime()
    {
        return episodeRunTime;
    }

    public void setEpisodeRunTime(String[] episodeRunTime)
    {
        this.episodeRunTime = episodeRunTime;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String[] getLanguages ()
    {
        return languages;
    }

    public void setLanguages (String[] languages)
    {
        this.languages = languages;
    }

    public String getNumberOfSeasons()
    {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(String numberOfSeasons)
    {
        this.numberOfSeasons = numberOfSeasons;
    }

    public String getLastAirDate()
    {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate)
    {
        this.lastAirDate = lastAirDate;
    }

    public String getInProduction()
    {
        return inProduction;
    }

    public void setInProduction(String inProduction)
    {
        this.inProduction = inProduction;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getPopularity ()
    {
        return popularity;
    }

    public void setPopularity (String popularity)
    {
        this.popularity = popularity;
    }

    public List<Networks> getNetworks ()
    {
        return networks;
    }

    public void setNetworks (List<Networks> networks)
    {
        this.networks = networks;
    }

    public Created_by[] getCreated_by ()
    {
        return created_by;
    }

    public void setCreated_by (Created_by[] created_by)
    {
        this.created_by = created_by;
    }

    public String getBackdropPath()
    {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath)
    {
        this.backdropPath = backdropPath;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getNumberOfEpisodes()
    {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(String numberOfEpisodes)
    {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public String getOriginalName()
    {
        return originalName;
    }

    public void setOriginalName(String originalName)
    {
        this.originalName = originalName;
    }

    public String getHomepage ()
    {
        return homepage;
    }

    public void setHomepage (String homepage)
    {
        this.homepage = homepage;
    }

    public String getFirstAirDate()
    {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate)
    {
        this.firstAirDate = firstAirDate;
    }

    public String[] getOriginCountry()
    {
        return originCountry;
    }

    public void setOriginCountry(String[] originCountry)
    {
        this.originCountry = originCountry;
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

    public void setProductionCompanies(List<ProductionCompanies> productionCompanies)
    {
        this.productionCompanies = productionCompanies;
    }

    public List<Seasons> getSeasons ()
    {
        return seasons;
    }

    public void setSeasons (List<Seasons> seasons)
    {
        this.seasons = seasons;
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

    @Override
    public String toString()
    {
        return "ClassPojo [voteAverage = "+ voteAverage +", genres = "+genres+", episodeRunTime = "+ episodeRunTime +", type = "+type+", id = "+id+", languages = "+languages+", numberOfSeasons = "+ numberOfSeasons +", lastAirDate = "+ lastAirDate +", inProduction = "+ inProduction +", name = "+name+", popularity = "+popularity+", networks = "+networks+", created_by = "+created_by+", backdropPath = "+ backdropPath +", status = "+status+", numberOfEpisodes = "+ numberOfEpisodes +", originalName = "+ originalName +", homepage = "+homepage+", firstAirDate = "+ firstAirDate +", originCountry = "+ originCountry +", originalLanguage = "+ originalLanguage +", overview = "+overview+", productionCompanies = "+ productionCompanies +", seasons = "+seasons+", images = "+images+", voteCount = "+ voteCount +", posterPath = "+ posterPath +"]";
    }
}