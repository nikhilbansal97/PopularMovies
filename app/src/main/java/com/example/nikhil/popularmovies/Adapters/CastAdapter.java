package com.example.nikhil.popularmovies.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nikhil.popularmovies.R;
import com.example.nikhil.popularmovies.pojos.movie_details.Cast;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by nikhil on 31/7/17.
 */

/**
 * This is a custom adapter which shows the cast information of a movie or tv show in a recycler view.
 */

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastHolder>{

    private Context context;
    private List<Cast> castList;

    public CastAdapter(Context context, List<Cast> castList) {
        this.context = context;
        this.castList = castList;
    }

    @Override
    public CastHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CastHolder(LayoutInflater.from(context).inflate(R.layout.cast_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(CastHolder holder, int position) {
        Cast cast = castList.get(position);
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500" + cast.getProfilePath()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(castList != null)
            return castList.size();
        else
            return 0;
    }

    public class CastHolder extends RecyclerView.ViewHolder{
        private CircleImageView imageView;
        public CastHolder(View itemView) {
            super(itemView);
            imageView = (CircleImageView) itemView.findViewById(R.id.circularImageView);
        }
    }

}
