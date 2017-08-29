package com.example.nikhil.popularmovies.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nikhil.popularmovies.R;
import com.example.nikhil.popularmovies.pojos.movie_details.Genres;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikhil on 12/7/17.
 */

/**
 * This is a custom adapter to show the genre information of a tv show or movie.
 */

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreHolder>{

    private Context mContext;
    private List<Genres> mList = new ArrayList<>();

    public GenreAdapter(Context context, List<Genres> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public GenreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GenreHolder(LayoutInflater.from(mContext).inflate(R.layout.genre_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(GenreHolder holder, int position) {
        Genres genres = mList.get(position);
        holder.genreText.setText(genres.getName());
    }

    @Override
    public int getItemCount() {
        if(mList != null)
            return mList.size();
        else
            return 0;
    }

    public class GenreHolder extends RecyclerView.ViewHolder{
        TextView genreText;
        public GenreHolder(View itemView) {
            super(itemView);
            genreText = (TextView) itemView.findViewById(R.id.textView_genre_listItem);
        }
    }

}
