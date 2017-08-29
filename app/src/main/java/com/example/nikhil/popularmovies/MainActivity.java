package com.example.nikhil.popularmovies;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Toast;

import com.example.nikhil.popularmovies.Adapters.MovieAdapter;
import com.example.nikhil.popularmovies.Adapters.TvAdapter;
import com.example.nikhil.popularmovies.Retrofit.ApiClient;
import com.example.nikhil.popularmovies.Retrofit.ApiInterface;
import com.example.nikhil.popularmovies.pojos.movie.Response;
import com.example.nikhil.popularmovies.pojos.movie.Results;
import com.example.nikhil.popularmovies.pojos.tv.PopularTv;
import com.example.nikhil.popularmovies.pojos.tv.TvResults;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements OnClickInterface,OnTvClickInterface{

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        movies = new ArrayList<>();
        tvShows = new ArrayList<>();
        if(savedInstanceState != null)
        {
            data_type = savedInstanceState.getString("data_type");
            if(data_type.equals("movies"))
                movies = savedInstanceState.getParcelableArrayList("movies_list");
            else
                tvShows = savedInstanceState.getParcelableArrayList("tv_list");
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

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this,2);
        layoutManager.scrollToPosition((int) visibleItemIndex);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new MovieAdapter(this,this,movies);
        tvAdapter = new TvAdapter(this,this,tvShows);
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
                        setTitle("Top Rated");
                        getMovies(SORT_RATING);
                        return true;
                    case R.id.menu_popular_shows :
                        Toast.makeText(MainActivity.this, "Popular Shows", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawers();
                        getTv(SORT_POPULARITY);
                        return true;
                    case R.id.menu_top_rated_shows :
                        Toast.makeText(MainActivity.this, "Top Rated Shows", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawers();
                        getTv(SORT_RATING);
                        return true;
                    default:
                        Toast.makeText(MainActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });
    }

    /**
     * Get list of tv shows.
     * @param path : Filter the tv shows according to popularity or top_rated.
     */
    public void getTv(String path){
        progressBar.setVisibility(View.VISIBLE);
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
                Log.d(TAG, "onResponse: "+recyclerView.toString());
            }

            @Override
            public void onFailure(Call<PopularTv> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed to get TV shows!");
            }
        });
        progressBar.setVisibility(View.GONE);
    }

    /**
     * Get list of movies list.
     * @param sort_by : Filter movies either by popular or top_rated.
     */
    public void getMovies(String sort_by){
        progressBar.setVisibility(View.VISIBLE);
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
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.v(TAG , "onFailure()");
            }
        });
        progressBar.setVisibility(View.GONE);
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
