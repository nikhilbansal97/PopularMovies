package com.example.nikhil.popularmovies;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.nikhil.popularmovies.Adapters.CastAdapter;
import com.example.nikhil.popularmovies.Adapters.GenreAdapter;
import com.example.nikhil.popularmovies.Adapters.PhotosAdapter;
import com.example.nikhil.popularmovies.Adapters.ReviewsAdapter;
import com.example.nikhil.popularmovies.Adapters.VideosAdapter;
import com.example.nikhil.popularmovies.Listeners.OnVideoClickInterface;
import com.example.nikhil.popularmovies.Retrofit.ApiClient;
import com.example.nikhil.popularmovies.Retrofit.ApiInterface;
import com.example.nikhil.popularmovies.database.DatabaseProvider;
import com.example.nikhil.popularmovies.database.MovieTable;
import com.example.nikhil.popularmovies.database.TvTable;
import com.example.nikhil.popularmovies.pojos.movie_details.Cast;
import com.example.nikhil.popularmovies.pojos.movie_details.Credits;
import com.example.nikhil.popularmovies.pojos.movie_details.Genres;
import com.example.nikhil.popularmovies.pojos.movie_details.MovieDetail;
import com.example.nikhil.popularmovies.pojos.movie_details.ReviewResults;
import com.example.nikhil.popularmovies.pojos.movie_details.Reviews;
import com.example.nikhil.popularmovies.pojos.movie_images.Backdrops;
import com.example.nikhil.popularmovies.pojos.movie_videos.VideoResults;
import com.example.nikhil.popularmovies.pojos.movie_videos.Videos;
import com.example.nikhil.popularmovies.pojos.tv.TvDetails;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements OnVideoClickInterface {

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
    @BindView(R.id.textView_movie_releaseDate)
    TextView textViewMovieReleaseDate;
    @BindView(R.id.recyclerView_videos)
    RecyclerView recyclerViewVideos;
    @BindView(R.id.textView_movie_videos_title)
    TextView textViewMovieVideos;
    @BindView(R.id.textView_minutes_text)
    TextView textViewMinutesText;
    @BindView(R.id.textView_division_view)
    TextView textViewDivisionView;
    @BindView(R.id.listView_reviews)
    ListView listView_reviews;
    @BindView(R.id.textView_movie_reviews_title)
    TextView textViewMovieReviewsTitle;
    @BindView(R.id.toggleButton_movie_favourite)
    ToggleButton toggleButtonFavourite;
    private PhotosAdapter mAdapter;
    private List<Backdrops> mPhotosList;
    private List<Genres> mGenresList;
    private List<Cast> mCastList;
    private List<VideoResults> mVideosList;
    private GenreAdapter mGenreAdapter;
    private ApiInterface apiInterface;
    private CastAdapter castAdapter;
    private VideosAdapter videoAdapter;
    private String data_type;
    private LayoutInflater inflator;
    private ViewGroup parentView;
    private List<ReviewResults> mReviewsList;
    private ReviewsAdapter mReviewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        inflator = LayoutInflater.from(this);
        parentView = findViewById(R.id.parent_detail_fragment);

        inflator.inflate(R.layout.loading_view, parentView, true);
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

        if (data_type.equals("movie"))
            getMovieDetails(id);
        else
            getTvDetails(id);
        credits = getCredits(id);

        mPhotosList = new ArrayList<>();
        mGenresList = new ArrayList<>();
        mCastList = new ArrayList<>();
        mVideosList = new ArrayList<>();
        mReviewsList = new ArrayList<>();

        mAdapter = new PhotosAdapter(this, mPhotosList);
        mGenreAdapter = new GenreAdapter(this, mGenresList);
        castAdapter = new CastAdapter(this, mCastList);
        videoAdapter = new VideosAdapter(this, this, mVideosList, mPhotosList);
        mReviewsAdapter = new ReviewsAdapter(this, mReviewsList);
    }

    /**
     * Get the tv show details.
     *
     * @param id : Id of the tv show whose details are needed.
     */
    private void getTvDetails(String id) {
        Call<TvDetails> call = apiInterface.getTvDetails(id, API_KEY, "images");
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
     *
     * @param detail : TvDetails object which contains the details of the tv show.
     */
    private void updateTvUi(final TvDetails detail) {
        parentView.removeAllViews();
        inflator.inflate(R.layout.activity_detail, parentView, true);
        ButterKnife.bind(this);

        imageViewMoviePoster.setImageAlpha(150);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerViewGenre.setAdapter(mGenreAdapter);
        recyclerViewGenre.setItemAnimator(new DefaultItemAnimator());
        recyclerViewGenre.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerViewCrew.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCrew.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCrew.setAdapter(castAdapter);

        recyclerViewVideos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewVideos.setItemAnimator(new DefaultItemAnimator());
        recyclerViewVideos.setAdapter(videoAdapter);

        listView_reviews.setAdapter(mReviewsAdapter);

        getVideos(id);
        getReviews(id);

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
        String[] runtime = detail.getEpisode_run_time();
        if (runtime.length > 0) {
            textViewMovieRuntimeValue.setText(runtime[0]);
            textViewMovieRuntimeValue.setVisibility(View.VISIBLE);
            textViewMinutesText.setVisibility(View.VISIBLE);
            textViewDivisionView.setVisibility(View.VISIBLE);
        } else {
            textViewMovieRuntimeValue.setVisibility(View.GONE);
            textViewMinutesText.setVisibility(View.GONE);
            textViewDivisionView.setVisibility(View.GONE);
            textViewMovieRuntimeValue.setText("");
        }


        String[] projetions = new String[]{
                TvTable.COLUMN_ID,
                TvTable.COLUMN_TEXT
        };
        final Cursor cursor = getContentResolver().query(DatabaseProvider.TvTableClass.CONTENT_URI, projetions, null, null, null);
        cursor.moveToFirst();
        boolean isFav = false;
        for (int i = 0; i < cursor.getCount(); i++) {
            String tv_id = cursor.getString(cursor.getColumnIndex(TvTable.COLUMN_TEXT));
            Log.d(TAG, "updateUi: " + tv_id + " " + detail.getId());
            if ((detail.getId()).equals(tv_id)) {
                isFav = true;
            }
            cursor.moveToNext();
        }
        if (isFav) {
            Log.d(TAG, "updateUi: is in database");
            toggleButtonFavourite.setChecked(true);
            toggleButtonFavourite.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_star_yellow));
        } else {
            Log.d(TAG, "updateUi: not in database");
            toggleButtonFavourite.setChecked(false);
            toggleButtonFavourite.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_star_grey));
        }
        toggleButtonFavourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    Log.d(TAG, "onCheckedChanged: true");
                    toggleButtonFavourite.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_star_yellow));
                    cursor.moveToFirst();
                    boolean fav = false;
                    for(int i=0; i < cursor.getCount();i++){
                        String id = cursor.getString(cursor.getColumnIndex(TvTable.COLUMN_TEXT));
                        if(detail.getId() == id) {
                            fav = true;
                        }
                    }
                    if (!fav) {
                        Log.d(TAG, "onCheckedChanged: adding to database");
                        ContentValues values = new ContentValues();
                        values.put(TvTable.COLUMN_TEXT, detail.getId());
                        getContentResolver().insert(DatabaseProvider.TvTableClass.CONTENT_URI, values);
                    }
                } else {
                    Log.d(TAG, "onCheckedChanged: false, deleting item");
                    toggleButtonFavourite.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_star_grey));
                    String where = TvTable.COLUMN_TEXT+ "=?";
                    String[] selection = new String[]{detail.getId()};
                    getContentResolver().delete(DatabaseProvider.TvTableClass.withId(detail.getId()),where,selection);
                }
            }
        });

    }

    /**
     * Get all the available videos for the movie.
     *
     * @param id : Id of the movie whose videos are needed.
     */
    private void getVideos(String id) {
        Call<Videos> call;
        if (data_type.equals("movie"))
            call = apiInterface.getVideos(id, API_KEY);
        else
            call = apiInterface.getTvVideos(id, API_KEY);

        call.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                Videos videos = response.body();
                List<VideoResults> results = new ArrayList<>();
                if (videos != null) {
                    results = videos.getResults();
                    mVideosList.clear();
                    if (results != null && results.size() > 0) {
                        mVideosList.addAll(results);
                        videoAdapter.notifyDataSetChanged();
                        updateVideo(results.get(0));
                        textViewMovieVideos.setVisibility(View.VISIBLE);
                    } else {
                        textViewMovieVideos.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed to get videos");
            }
        });
    }

    /**
     * Get the user reviews for a particular movie / tv season.
     *
     * @param id : id of the tv show / movie whose reviews are needed.
     */
    private void getReviews(String id) {
        Call<Reviews> call;
        if (data_type.equals("movie"))
            call = apiInterface.getReviews("movie", id, API_KEY);
        else
            call = apiInterface.getReviews("tv", id, API_KEY);
        call.enqueue(new Callback<Reviews>() {
            @Override
            public void onResponse(Call<Reviews> call, Response<Reviews> response) {
                Log.d(TAG, "onResponse: " + response);
                Reviews reviews = response.body();
                if (reviews != null) {
                    ReviewResults[] results = reviews.getResults();
                    if (results != null && results.length > 0) {
                        textViewMovieReviewsTitle.setVisibility(View.VISIBLE);
                        listView_reviews.setVisibility(View.VISIBLE);
                        mReviewsList.clear();
                        Log.d(TAG, "onResponse: " + results.length);
                        for (int i = 0; i < results.length; i++) {
                            ReviewResults result = results[i];
                            Log.d(TAG, "onResponse: " + result.getContent());
                            mReviewsList.add(i, result);
                            mReviewsAdapter.notifyDataSetChanged();
                        }

                    } else {
                        Log.d(TAG, "onResponse: results is null or size is zero");
                        textViewMovieReviewsTitle.setVisibility(View.GONE);
                        listView_reviews.setVisibility(View.GONE);
                    }
                } else {
                    Log.d(TAG, "onResponse: reviews are null");
                    textViewMovieReviewsTitle.setVisibility(View.GONE);
                    listView_reviews.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Reviews> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed to get Reviews");
            }
        });
    }

    /**
     * Get the video key, append it in the youtube url and setup onClickListener for the video playback on Youtube Player.
     *
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
     *
     * @param id : Id of the tv show or the movie whose information is needed.
     * @return
     */
    @Nullable
    private Credits getCredits(String id) {
        Log.d(TAG, "getMovieCredits: ");
        Call<Credits> call;
        if (data_type.equals("movie"))
            call = apiInterface.getMovieCredits(id, API_KEY);
        else
            call = apiInterface.getTvCredits(id, API_KEY);

        call.enqueue(new Callback<Credits>() {
            @Override
            public void onResponse(Call<Credits> call, Response<Credits> response) {
                Credits credits = response.body();
                List<Cast> cast = new ArrayList<>();
                if (credits != null)
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
     *
     * @param cast : List of all the cast members of the tv show or the movie.
     */
    private void updateCast(List<Cast> cast) {
        if (cast != null) {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    /**
     * Get the details of a particular movie.
     *
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
     *
     * @param detail : MovieDetail object which contains the movie details.
     */
    private void updateUi(final MovieDetail detail) {
        parentView.removeAllViews();
        inflator.inflate(R.layout.activity_detail, parentView, true);
        ButterKnife.bind(this);
        imageViewMoviePoster.setImageAlpha(150);

        String[] projetions = new String[]{
                MovieTable.COLUMN_ID,
                MovieTable.COLUMN_MOVIE_ID
        };
        final Cursor cursor = getContentResolver().query(DatabaseProvider.MovieTableClass.CONTENT_URI, projetions, null, null, null);
        cursor.moveToFirst();
        boolean isFav = false;
        for (int i = 0; i < cursor.getCount(); i++) {
            String movie_id = cursor.getString(cursor.getColumnIndex(MovieTable.COLUMN_MOVIE_ID));
            Log.d(TAG, "updateUi: " + movie_id + " " + detail.getId());
            if ((detail.getId()).equals(movie_id)) {
                isFav = true;
            }
            cursor.moveToNext();
        }
        if (isFav) {
            Log.d(TAG, "updateUi: is in database");
            toggleButtonFavourite.setChecked(true);
            toggleButtonFavourite.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_star_yellow));
        } else {
            Log.d(TAG, "updateUi: not in database");
            toggleButtonFavourite.setChecked(false);
            toggleButtonFavourite.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_star_grey));
        }
        toggleButtonFavourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    Log.d(TAG, "onCheckedChanged: true");
                    toggleButtonFavourite.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_star_yellow));
                    cursor.moveToFirst();
                    boolean fav = false;
                    for(int i=0; i < cursor.getCount();i++){
                        String id = cursor.getString(cursor.getColumnIndex(MovieTable.COLUMN_MOVIE_ID));
                        if(detail.getId() == id) {
                            fav = true;
                        }
                    }
                    if (!fav) {
                        Log.d(TAG, "onCheckedChanged: adding to database");
                        ContentValues values = new ContentValues();
                        values.put(MovieTable.COLUMN_MOVIE_ID, detail.getId());
                        getContentResolver().insert(DatabaseProvider.MovieTableClass.CONTENT_URI, values);
                    }
                } else {
                    Log.d(TAG, "onCheckedChanged: false, deleting item");
                    toggleButtonFavourite.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_star_grey));
                    String where = MovieTable.COLUMN_MOVIE_ID + "=?";
                    String[] selection = new String[]{detail.getId()};
                    getContentResolver().delete(DatabaseProvider.MovieTableClass.withId(detail.getId()),where,selection);
                }
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerViewGenre.setAdapter(mGenreAdapter);
        recyclerViewGenre.setItemAnimator(new DefaultItemAnimator());
        recyclerViewGenre.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerViewCrew.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCrew.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCrew.setAdapter(castAdapter);

        recyclerViewVideos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewVideos.setItemAnimator(new DefaultItemAnimator());
        recyclerViewVideos.setAdapter(videoAdapter);

        listView_reviews.setAdapter(mReviewsAdapter);

        getVideos(detail.getId());
        getReviews(detail.getId());
        Log.d(TAG, "updateUi: " + detail.getId());

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
        SimpleDateFormat oldDate = new SimpleDateFormat("yyyy-dd-mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy ");
        String date = detail.getRelease_date();
        try {
            date = dateFormat.format(oldDate.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        textViewMovieReleaseDate.setText(date);
    }

    /**
     * Handle video clicks in the videos section
     *
     * @param result
     */
    @Override
    public void OnVideoClick(VideoResults result) {
        String key = result.getKey();
        final String url = "https://www.youtube.com/watch?v=" + key;
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

}
