package com.example.nikhil.popularmovies.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.nikhil.popularmovies.OnClickInterface;
import com.example.nikhil.popularmovies.R;
import com.example.nikhil.popularmovies.pojos.movie.Results;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a custom adapter which shows the list of movies in a recycler view.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    private static final String TAG = "MovieAdapter";
    private Context mContext;
    private List<Results> mList = new ArrayList<>();
    private OnClickInterface mInterface;

    public MovieAdapter(Context context, OnClickInterface clickInterface, List<Results> results) {

        mList = results;
        mContext = context;
        mInterface = clickInterface;
        if (mList != null)
            Log.v(TAG,String.valueOf(mList.size()));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Results movie = mList.get(position);
        Glide.with(mContext).load("https://image.tmdb.org/t/p/w500"+movie.getPosterPath()).into(holder.movie_poster);
        holder.movie_poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterface.onClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mList != null){
            Log.d(TAG, String.valueOf(mList.size()));
            return mList.size();
        }
        else
            return  0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView movie_poster;
        public ProgressBar progress;
        public ViewHolder(View itemView) {
            super(itemView);
            movie_poster = (ImageView) itemView.findViewById(R.id.imageView_listItem);
            progress = (ProgressBar) itemView.findViewById(R.id.progressBar_listItem);
        }
    }

}
