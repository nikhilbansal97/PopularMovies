package com.example.nikhil.popularmovies;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nikhil.popularmovies.Adapters.MovieAdapter;
import com.example.nikhil.popularmovies.Adapters.TvAdapter;
import com.example.nikhil.popularmovies.Listeners.OnClickInterface;
import com.example.nikhil.popularmovies.Listeners.OnTvClickInterface;
import com.example.nikhil.popularmovies.Retrofit.ApiClient;
import com.example.nikhil.popularmovies.Retrofit.ApiInterface;
import com.example.nikhil.popularmovies.database.DatabaseProvider;
import com.example.nikhil.popularmovies.database.MovieTable;
import com.example.nikhil.popularmovies.database.TvTable;
import com.example.nikhil.popularmovies.pojos.movie.Response;
import com.example.nikhil.popularmovies.pojos.movie.Results;
import com.example.nikhil.popularmovies.pojos.movie_details.MovieDetail;
import com.example.nikhil.popularmovies.pojos.tv.PopularTv;
import com.example.nikhil.popularmovies.pojos.tv.TvDetails;
import com.example.nikhil.popularmovies.pojos.tv.TvResults;
import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements OnClickInterface,OnTvClickInterface {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    public static final String SORT_POPULARITY = "popular";
    public static final String SORT_RATING = "top_rated";
    public static final String API_KEY = "e29ebd1566209e1d0a4279df87798d4f";
    private List<Results> movies;
    private List<TvResults> tvShows;
    private ProgressBar progressBar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private TvAdapter tvAdapter;
    private String data_type;
    private long visibleItemIndex = 0;
    private GridLayoutManager layoutManager;
    private TextView textView_networkOffline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView_networkOffline = (TextView) findViewById(R.id.textView_networkOffline);
        progressBar.setVisibility(View.VISIBLE);
        movies = new ArrayList<>();
        tvShows = new ArrayList<>();

        if(savedInstanceState != null)
        {
            data_type = savedInstanceState.getString("data_type");
            if(data_type.equals("movies")) {
                movies = savedInstanceState.getParcelableArrayList("movies_list");
            }
            else {
                tvShows = savedInstanceState.getParcelableArrayList("tv_list");
            }
            setTitle(savedInstanceState.getString("title"));
            visibleItemIndex = savedInstanceState.getLong("index");
            progressBar.setVisibility(View.GONE);
        }
        getSupportActionBar().setElevation(0);

        // Bind Views
        navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setItemIconTintList(null);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawer,R.string.open,R.string.close);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /** Setup Stetho Library */
        Stetho.initializeWithDefaults(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.scrollToPosition((int) visibleItemIndex);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new MovieAdapter(this,this,movies);
        tvAdapter = new TvAdapter(this,this,tvShows);

        // Check for network connectivity
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetorkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetorkInfo != null && !activeNetorkInfo.isConnected())
        {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            textView_networkOffline.setVisibility(View.VISIBLE);
        } else  {
            textView_networkOffline.setVisibility(View.GONE);
        }

        if(savedInstanceState != null)
        {
            if(!data_type.equals("movies"))
                recyclerView.setAdapter(tvAdapter);
            else
                recyclerView.setAdapter(adapter);
        } else
            recyclerView.setAdapter(adapter);

        setupNavigationItemClick();
        adapter.notifyDataSetChanged();

        MenuItem menuItem = navigationView.getMenu().findItem(R.id.menu_title_movies);
        SpannableString s = new SpannableString(menuItem.getTitle());
        s.setSpan(new ForegroundColorSpan(Color.WHITE),0,s.length(),0);
        s.setSpan(new AbsoluteSizeSpan(20,true),0,s.length(),0);
        menuItem.setTitle(s);

        MenuItem item = navigationView.getMenu().findItem(R.id.menu_title_tvShows);
        SpannableString tv = new SpannableString(item.getTitle());
        tv.setSpan(new ForegroundColorSpan(Color.WHITE),0,tv.length(),0);
        tv.setSpan(new AbsoluteSizeSpan(20,true),0,tv.length(),0);
        item.setTitle(tv);

        // Retrofit Call
        if(savedInstanceState == null)
            getMovies(SORT_POPULARITY);
    }

    /**
     * Handle Navigation Bar item clicks.
     */
    private void setupNavigationItemClick() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.menu_popular_movies :
                        drawer.closeDrawers();
                        setTitle("Popular Movies");
                        getMovies(SORT_POPULARITY);
                        return true;
                    case R.id.menu_top_rated :
                        drawer.closeDrawers();
                        setTitle("Top Rated Movies");
                        getMovies(SORT_RATING);
                        return true;
                    case R.id.menu_popular_shows :
                        drawer.closeDrawers();
                        setTitle("Popular Tv Shows");
                        getTv(SORT_POPULARITY);
                        return true;
                    case R.id.menu_top_rated_shows :
                        setTitle("Top Rated Tv Shows");
                        drawer.closeDrawers();
                        getTv(SORT_RATING);
                        return true;
                    case R.id.menu_favourite_movie :
                        setTitle("Favourite Movies");
                        String[] projection = new String[]{
                                MovieTable.COLUMN_ID,
                                MovieTable.COLUMN_MOVIE_ID
                        };
                        Cursor cursor = getContentResolver().query(DatabaseProvider.MovieTableClass.CONTENT_URI,projection,null,null,null);
                        cursor.moveToFirst();
                        ArrayList<String> movie_ids = new ArrayList<String>();
                        for(int i=0;i< cursor.getCount();i++){
                            String id = cursor.getString(cursor.getColumnIndex(MovieTable.COLUMN_MOVIE_ID));
                            Log.d(TAG, "onNavigationItemSelected: " + id);
                            cursor.moveToNext();
                            movie_ids.add(id);
                        }
                        drawer.closeDrawers();
                        getFavouriteMoviesList(movie_ids);
                        Log.d(TAG, "onNavigationItemSelected: favourite");
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        return true;
                    case R.id.menu_favourite_tv :
                        setTitle("Favourite Tv Shows");
                        String[] projection_tv = new String[]{
                                TvTable.COLUMN_ID,
                                TvTable.COLUMN_TEXT
                        };
                        Cursor cursor_tv = getContentResolver().query(DatabaseProvider.TvTableClass.CONTENT_URI,projection_tv,null,null,null);
                        cursor_tv.moveToFirst();
                        ArrayList<String> tv_ids = new ArrayList<String>();
                        for(int i=0;i< cursor_tv.getCount();i++){
                            String id = cursor_tv.getString(cursor_tv.getColumnIndex(TvTable.COLUMN_TEXT));
                            Log.d(TAG, "onNavigationItemSelected: " + id);
                            cursor_tv.moveToNext();
                            tv_ids.add(id);
                        }
                        drawer.closeDrawers();
                        getFavouriteTvList(tv_ids);
                        Log.d(TAG, "onNavigationItemSelected: favourite");
                        recyclerView.setAdapter(tvAdapter);
                        tvAdapter.notifyDataSetChanged();
                        return true;
                    default:
                        Toast.makeText(MainActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });
    }

    private void getFavouriteTvList(ArrayList<String> tv_ids) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<TvDetails> callTvDetail;
        tvShows.clear();
        Log.d(TAG, "getFavouriteMoviesList: " + tvShows.size());
        for(int i=0; i< tv_ids.size(); i++){
            callTvDetail = apiInterface.getTvDetails(tv_ids.get(i),API_KEY,"images");
            callTvDetail.enqueue(new Callback<TvDetails>() {
                @Override
                public void onResponse(Call<TvDetails> call, retrofit2.Response<TvDetails> response) {
                    if(response != null && response.body() != null){
                        TvDetails detail = response.body();
                        String id = detail.getId();
                        String image_short = detail.getPoster_path();
                        String title = detail.getName();
                        TvResults result = new TvResults(id,image_short,title);
                        Log.d(TAG, "onResponse: " + tvShows.size());
                        tvShows.add(result);
                        tvAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<TvDetails> call, Throwable t) {

                }
            });

            if(i == tv_ids.size() - 1) {
                adapter.notifyDataSetChanged();
                Log.d(TAG, "getFavouriteMoviesList: adapter updated");
            }
        }

        if(tvShows.size() > 0) {
            Log.d(TAG, "getFavouriteMoviesList: size : " + tvShows.size());
            recyclerView.setAdapter(tvAdapter);
            tvAdapter.notifyDataSetChanged();
        }
    }


    private void getFavouriteMoviesList(final ArrayList<String> movie_ids) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieDetail> callMovieDetail;
        movies.clear();
        Log.d(TAG, "getFavouriteMoviesList: " + movies.size());
        for(int i=0; i< movie_ids.size(); i++){
            callMovieDetail = apiInterface.getMovieDetails(movie_ids.get(i),API_KEY,"images");
            callMovieDetail.enqueue(new Callback<MovieDetail>() {
                @Override
                public void onResponse(Call<MovieDetail> call, retrofit2.Response<MovieDetail> response) {
                    if(response != null && response.body() != null){
                        MovieDetail detail = response.body();
                        String id = detail.getId();
                        String image_short = detail.getPoster_path();
                        String title = detail.getTitle();
                        Results result = new Results(id,image_short,title);
                        Log.d(TAG, "onResponse: " + movies.size());
                        movies.add(result);
                        adapter.notifyDataSetChanged();
                    }
                }
                @Override
                public void onFailure(Call<MovieDetail> call, Throwable t) {
                    Log.d(TAG, "onFailure: Failed to get movies from favourites");
                }
            });
            if(i == movie_ids.size() - 1) {
                adapter.notifyDataSetChanged();
                Log.d(TAG, "getFavouriteMoviesList: adapter updated");
            }
        }

        if(movies.size() > 0) {
            Log.d(TAG, "getFavouriteMoviesList: size : " + movies.size());
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }


    /**
     * Get list of tv shows.
     * @param path : Filter the tv shows according to popularity or top_rated.
     */
    public void getTv(String path){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<PopularTv> call = apiInterface.getTv(path,API_KEY);
        call.enqueue(new Callback<PopularTv>() {
            @Override
            public void onResponse(Call<PopularTv> call, retrofit2.Response<PopularTv> response) {
                tvShows.clear();
                PopularTv responseTv = response.body();
                if(responseTv != null) {
                    tvShows.addAll(responseTv.getResults());
                    for(int i=0; i<tvShows.size();i++)
                    {
                        TvResults result = tvShows.get(i);
                        Log.d(TAG, result.getOriginal_name());
                        if(!result.getOriginal_language().equals("en"))
                            tvShows.remove(i);
                    }
                }
                recyclerView.setAdapter(tvAdapter);
                tvAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                Log.d(TAG, "onResponse: "+recyclerView.toString());
            }

            @Override
            public void onFailure(Call<PopularTv> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed to get TV shows!");
            }
        });
    }

    /**
     * Get list of movies list.
     * @param sort_by : Filter movies either by popular or top_rated.
     */
    public void getMovies(String sort_by){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Response> call = apiInterface.getMovies(sort_by,API_KEY);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                movies.clear();
                Response response1 = response.body();
                movies.addAll(response1.getResults());
                for(int i=0;i<movies.size();i++)
                {
                    if(movies.get(i).getPoster_path() == null ||
                            movies.get(i).getPoster_path().contains("null") ||
                            movies.get(i).equals("null") ||
                            !movies.get(i).getOriginal_language().equals("en"))
                        movies.remove(i);
                }
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.v(TAG , "onFailure()");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    /**
     * Save the instance of the state when user navigates away from the activity.
     * @param outState : Bundle in which the state is saved.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(recyclerView.toString().contains("TvAdapter")){
            outState.putString("data_type","tv_shows");
            outState.putParcelableArrayList("tv_list", (ArrayList<TvResults>) tvShows);
        }
        else {
            outState.putString("data_type","movies");
            outState.putParcelableArrayList("movies_list", (ArrayList<Results>) movies);
        }
        long index = layoutManager.findFirstCompletelyVisibleItemPosition();
        outState.putLong("index",index);
        outState.putString("title", (String) getTitle());
    }

    /**
     * Callback method for OnItemClickListener which handles the Movie item clicks.
     * @param movie : Movie item which was clicked.
     */
    @Override
    public void onClick(Results movie) {
        // If movie received is not null
        if(movie != null)
        {
            // Open Details Activity to show the details of the movie.
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("id",movie.getId());
            intent.putExtra("image_short", movie.getPoster_path());
            intent.putExtra("title", movie.getTitle());
            intent.putExtra("data_type","movie");
            startActivity(intent);
        } else {
            Log.d(TAG, "Movie clicked is null!");
        }
    }

    /**
     * Callback method for the OnTvClickInterface to handle tv show item clicks.
     * @param result : Tv show that was clicked.
     */
    @Override
    public void onTvItemClick(TvResults result) {
        // If tv show is not null
        if(result != null){
            // Open Detail activity to show the details of the tv show.
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("id",result.getId());
            intent.putExtra("image_short",result.getPoster_path());
            intent.putExtra("title",result.getOriginal_name());
            intent.putExtra("data_type","tv_show");
            startActivity(intent);
        } else {
            Log.d(TAG, "Tv clicked is null");
        }
    }
}
