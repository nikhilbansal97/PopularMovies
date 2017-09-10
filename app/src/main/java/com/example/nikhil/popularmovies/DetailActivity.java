package com.example.nikhil.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nikhil.popularmovies.Adapters.CastAdapter;
import com.example.nikhil.popularmovies.Adapters.GenreAdapter;
import com.example.nikhil.popularmovies.Adapters.PhotosAdapter;
import com.example.nikhil.popularmovies.Retrofit.ApiClient;
import com.example.nikhil.popularmovies.Retrofit.ApiInterface;
import com.example.nikhil.popularmovies.pojos.movie_details.Cast;
import com.example.nikhil.popularmovies.pojos.movie_details.Credits;
import com.example.nikhil.popularmovies.pojos.movie_details.Genres;
import com.example.nikhil.popularmovies.pojos.movie_details.MovieDetail;
import com.example.nikhil.popularmovies.pojos.movie_images.Backdrops;
import com.example.nikhil.popularmovies.pojos.movie_videos.VideoResults;
import com.example.nikhil.popularmovies.pojos.movie_videos.Videos;
import com.example.nikhil.popularmovies.pojos.tv.TvDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";
    @BindView(R.id.imageView_movie_poster)
    ImageView imageViewMoviePoster;
    @BindView(R.id.imageView_movie_poster_small)
    ImageView imageViewMoviePosterSmall;
    @BindView(R.id.cardView_movie_poster_small)
    CardView cardViewMoviePosterSmall;
    @BindView(R.id.textView_movie_title)
    TextView textViewMovieTitle;
    @BindView(R.id.textView_movie_rating_value)
    TextView textViewMovieRatingValue;
    @BindView(R.id.textView_movie_rating_title)
    TextView textViewMovieRatingTitle;
    @BindView(R.id.textView_movie_storyline_title)
    TextView textViewMovieStorylineTitle;
    public String id;
    public static final String API_KEY = "e29ebd1566209e1d0a4279df87798d4f";
    public Credits credits;
    public String image_url_short;
    public Context mContext;
    public String title;
    @BindView(R.id.textView_movie_overview_value)
    TextView textViewMovieOverviewValue;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.recyclerView_genre)
    RecyclerView recyclerViewGenre;
    @BindView(R.id.textView_movie_runtime_value)
    TextView textViewMovieRuntimeValue;
    @BindView(R.id.textView_movie_photos_title)
    TextView textViewMoviePhotosTitle;
    @BindView(R.id.textView_movie_cast_title)
    TextView textViewMovieCastTitle;
    @BindView(R.id.recyclerView_crew)
    RecyclerView recyclerViewCrew;
    private PhotosAdapter mAdapter;
    private List<Backdrops> mPhotosList;
    private List<Genres> mGenresList;
    private List<Cast> mCastList;
    private GenreAdapter mGenreAdapter;
    private ApiInterface apiInterface;
    private CastAdapter castAdapter;
    private String data_type;
    private LayoutInflater inflator;
    private ViewGroup parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        inflator = LayoutInflater.from(this);
        parentView = findViewById(R.id.parent_detail_fragment);

        inflator.inflate(R.layout.loading_view, parentView,true);
        mContext = this;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        credits = new Credits();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        image_url_short = intent.getStringExtra("image_short");
        title = intent.getStringExtra("title");
        data_type = intent.getStringExtra("data_type");
        setTitle(title);

        Log.d(TAG, id + title + data_type);

        if(data_type.equals("movie"))
            getMovieDetails(id);
        else
            getTvDetails(id);
        credits = getCredits(id);
        getVideos(id);
        mPhotosList = new ArrayList<>();
        mGenresList = new ArrayList<>();
        mCastList = new ArrayList<>();

        mAdapter = new PhotosAdapter(this, mPhotosList);
        mGenreAdapter = new GenreAdapter(this, mGenresList);
        castAdapter = new CastAdapter(this, mCastList);

    }

    /**
     * Get the tv show details.
     * @param id : Id of the tv show whose details are needed.
     */
    private void getTvDetails(String id) {
        Call<TvDetails> call = apiInterface.getTvDetails(id,API_KEY,"images");
        call.enqueue(new Callback<TvDetails>() {
            @Override
            public void onResponse(Call<TvDetails> call, Response<TvDetails> response) {
                TvDetails detail = response.body();
                updateTvUi(detail);
            }
            @Override
            public void onFailure(Call<TvDetails> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed to retrieve tv details!");
            }
        });
    }

    /**
     * Update the UI to show the information of the tv show.
     * @param detail : TvDetails object which contains the details of the tv show.
     */
    private void updateTvUi(TvDetails detail) {
        parentView.removeAllViews();
        inflator.inflate(R.layout.activity_detail, parentView,true);
        ButterKnife.bind(this);
        imageViewMoviePoster.setImageAlpha(150);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerViewGenre.setAdapter(mGenreAdapter);
        recyclerViewGenre.setItemAnimator(new DefaultItemAnimator());
        recyclerViewGenre.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerViewCrew.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCrew.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCrew.setAdapter(castAdapter);

        Picasso.with(mContext).load("https://image.tmdb.org/t/p/w500" + detail.getBackdrop_path()).into(imageViewMoviePoster);
        Picasso.with(mContext).load("https://image.tmdb.org/t/p/w500" + image_url_short).centerCrop().resize(225, 400).into(imageViewMoviePosterSmall);
        textViewMovieTitle.setText(detail.getOriginal_name());
        textViewMovieRatingValue.setText(detail.getVote_average());
        textViewMovieOverviewValue.setText(detail.getOverview());
        mPhotosList.clear();
        mPhotosList.addAll(detail.getImages().getBackdrops());
        mPhotosList.remove(0);
        mAdapter.notifyDataSetChanged();
        mGenresList.clear();
        mGenresList.addAll(detail.getGenres());
        mGenreAdapter.notifyDataSetChanged();
        textViewMovieRuntimeValue.setText("");
        textViewMovieRuntimeValue.setVisibility(View.GONE);
    }

    /**
     * Get all the available videos for the movie.
     * @param id : Id of the movie whose videos are needed.
     */
    private void getVideos(String id) {
        Call<Videos> call;
        if(data_type.equals("movie"))
            call = apiInterface.getVideos(id,API_KEY);
        else
            call = apiInterface.getTvVideos(id,API_KEY);

        call.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                Videos videos = response.body();
                List<VideoResults> results = new ArrayList<>();
                results = videos.getResults();
                if(results != null && results.size() > 0)
                    updateVideo(results.get(0));
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed to get videos");
            }
        });
    }

    /**
     * Get the video key, append it in the youtube url and setup onClickListener for the video playback on Youtube Player.
     * @param videoResults : Contains the key to the video of the tv show or movie.
     */
    private void updateVideo(VideoResults videoResults) {
        String key = videoResults.getKey();
        final String url = "https://www.youtube.com/watch?v=" + key;
        imageViewMoviePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });
    }

    /**
     * Get the credits information of the tv show or the movie. It includes the crew and cast information.
     * @param id : Id of the tv show or the movie whose information is needed.
     * @return
     */
    @Nullable
    private Credits getCredits(String id) {
        Log.d(TAG, "getMovieCredits: ");
        Call<Credits> call;
        if(data_type.equals("movie"))
            call = apiInterface.getMovieCredits(id,API_KEY);
        else
            call = apiInterface.getTvCredits(id,API_KEY);

        call.enqueue(new Callback<Credits>() {
            @Override
            public void onResponse(Call<Credits> call, Response<Credits> response) {
                Credits credits = response.body();
                List<Cast> cast = new ArrayList<>();
                if(credits != null)
                    cast = credits.getCast();
                updateCast(cast);
            }
            @Override
            public void onFailure(Call<Credits> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed to fetch data");
            }
        });
        return null;
    }

    /**
     * Update the UI to show the cast members of the tv show or movie.
     * @param cast : List of all the cast members of the tv show or the movie.
     */
    private void updateCast(List<Cast> cast) {
        if(cast != null) {
            List<Cast> list_cast = new ArrayList<>();
            int count = 0;
            while (count < 10 && count < cast.size()) {
                list_cast.add(cast.get(count));
                count++;
            }
            mCastList.clear();
            mCastList.addAll(list_cast);
            Log.d(TAG, list_cast.toString());
            castAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Get the details of a particular movie.
     * @param id : Id of the movie whose details are needed.
     * @return
     */
    private MovieDetail getMovieDetails(String id) {
        Log.d(TAG, "getMovieDetails: " + id);
        Call<MovieDetail> call = apiInterface.getMovieDetails(id, API_KEY, "images");
        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                Log.d(TAG, "onResponse: ");
                MovieDetail detail;
                detail = response.body();
                Log.d(TAG, detail.toString());
                updateUi(detail);
            }
            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
        return null;
    }

    /**
     * Update the UI to show the details of the movie
     * @param detail : MovieDetail object which contains the movie details.
     */
    private void updateUi(MovieDetail detail) {
        parentView.removeAllViews();
        inflator.inflate(R.layout.activity_detail, parentView,true);
        ButterKnife.bind(this);
        imageViewMoviePoster.setImageAlpha(150);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerViewGenre.setAdapter(mGenreAdapter);
        recyclerViewGenre.setItemAnimator(new DefaultItemAnimator());
        recyclerViewGenre.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerViewCrew.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCrew.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCrew.setAdapter(castAdapter);
        textViewMovieRuntimeValue.setVisibility(View.VISIBLE);
        Picasso.with(mContext).load("https://image.tmdb.org/t/p/w500" + detail.getBackdrop_path()).into(imageViewMoviePoster);
        Picasso.with(mContext).load("https://image.tmdb.org/t/p/w500" + image_url_short).centerCrop().resize(225, 400).into(imageViewMoviePosterSmall);
        textViewMovieTitle.setText(detail.getOriginal_title());
        textViewMovieRatingValue.setText(detail.getVote_average());
        textViewMovieOverviewValue.setText(detail.getOverview());
        mPhotosList.clear();
        mPhotosList.addAll(detail.getImages().getBackdrops());
        mPhotosList.remove(0);
        mAdapter.notifyDataSetChanged();
        mGenresList.clear();
        mGenresList.addAll(detail.getGenres());
        mGenreAdapter.notifyDataSetChanged();
        textViewMovieRuntimeValue.setText(detail.getRuntime());
    }
}
