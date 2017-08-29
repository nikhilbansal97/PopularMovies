package com.example.nikhil.popularmovies.Retrofit;

import com.example.nikhil.popularmovies.pojos.movie.Response;
import com.example.nikhil.popularmovies.pojos.movie_details.Credits;
import com.example.nikhil.popularmovies.pojos.movie_details.MovieDetail;
import com.example.nikhil.popularmovies.pojos.movie_videos.Videos;
import com.example.nikhil.popularmovies.pojos.tv.PopularTv;
import com.example.nikhil.popularmovies.pojos.tv.TvDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Interface where all the GET and POST methods are defined which will be called to get the serialized JSON response.
 */
public interface ApiInterface {

    /**
     * GET method for list of movies.
     * @param sort : Define the type of movies needed. Either popular or top_rated.
     * @param key : Pass your api key.
     * @return : JSON response serialized into pojo.
     */

    @GET("3/movie/{movie}")
    Call<Response> getMovies(@Path("movie") String sort,
                             @Query("api_key") String key);


    /**
     * GET method for getting list of tv shows.
     * @param tv : Filter tv shows according to popular ot top_rated.
     * @param key : Pass your api key.
     * @return : JSON response serialized into pojo.
     */

    @GET("/3/tv/{tv}")
    Call<PopularTv> getTv(@Path("tv") String tv,
                          @Query("api_key") String key);


    /**
     * GET method for details of a particular movie.
     * @param id : Id of the movie whose details are needed.
     * @param key : Your api key.
     * @param append : Any extra information you want to append in the response.
     * @return
     */

    @GET("3/movie/{movie_id}")
    Call<MovieDetail> getMovieDetails(@Path("movie_id") String id,
                                      @Query("api_key") String key,
                                      @Query("append_to_response") String append);

    /**
     * GET method for details of a particular tv show.
     * @param id : Id of the tv show whose details are required.
     * @param key : Your api key.
     * @param append : Extra information that you want to append into the response.
     * @return
     */

    @GET("/3/tv/{tv_id}")
    Call<TvDetails> getTvDetails(@Path("tv_id") String id,
                                 @Query("api_key") String key,
                                 @Query("append_to_response") String append);

    /**
     * GET method to receive videos available for a particular movie.
     * @param id : Id of the movie whose videos are required.
     * @param key : Your api key.
     * @return
     */

    @GET("3/movie/{movie_id}/videos")
    Call<Videos> getVideos(@Path("movie_id") String id,
                           @Query("api_key") String key);

    /**
     *  GET method for getting the videos available for a particular tv show.
     * @param id : Id of the tv show whose videos are required.
     * @param key : Your api key.
     * @return
     */

    @GET("3/tv/{tv_id}/videos")
    Call<Videos> getTvVideos(@Path("tv_id") String id,
                             @Query("api_key") String key);

    /**
     * GET method for getting the Credits information of a particular movie such as cast and crew.
     * @param id : Id of the movie whose credits information is needed.
     * @param key : Your api key.
     * @return
     */

    @GET("3/movie/{movie_id}/credits")
    Call<Credits> getMovieCredits(@Path("movie_id") String id,
                                  @Query("api_key") String key);

    /**
     * GET method to get the credits information of a particular th show such as cast and crew.
     * @param id : Id of the tv show whose credits information is needed.
     * @param key : Your api key.
     * @return
     */

    @GET("3/tv/{tv_id}/credits")
    Call<Credits> getTvCredits(@Path("tv_id") String id,
                               @Query("api_key") String key);


}
