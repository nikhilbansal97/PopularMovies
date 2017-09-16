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
import com.example.nikhil.popularmovies.Listeners.OnTvClickInterface;
import com.example.nikhil.popularmovies.R;
import com.example.nikhil.popularmovies.pojos.tv.TvResults;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a custom adapter to show the list of Tv Shows in a recycler view.
 */

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder>{

    private Context context;
    private OnTvClickInterface mInterface;
    private List<TvResults> tvShows = new ArrayList<>();
    private static final String TAG = "TvAdapter";

    public TvAdapter(Context context, OnTvClickInterface mInterface, List<TvResults> tvShows) {
        this.context = context;
        this.mInterface = mInterface;
        this.tvShows = tvShows;
    }

    @Override
    public TvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TvViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(TvViewHolder holder, int position) {
        final TvResults result = tvShows.get(position);
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+result.getPoster_path()).into(holder.imageView);
        Log.d(TAG, result.getOriginal_name());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterface.onTvItemClick(result);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(tvShows != null)
            return tvShows.size();
        else
            return 0;
    }

    public class TvViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private ProgressBar progressBar;
        public TvViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView_listItem);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar_listItem);
        }
    }

}
