package com.example.nikhil.popularmovies.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.nikhil.popularmovies.Listeners.OnVideoClickInterface;
import com.example.nikhil.popularmovies.R;
import com.example.nikhil.popularmovies.pojos.movie_images.Backdrops;
import com.example.nikhil.popularmovies.pojos.movie_videos.VideoResults;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by nikhil on 11/9/17.
 */

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideoHolder>{

    private Context mContext;
    private List<VideoResults> mList;
    private List<Backdrops> mPhotosList;
    private OnVideoClickInterface videoClickInterface;

    public VideosAdapter(Context context,OnVideoClickInterface videoInterface ,List<VideoResults> list, List<Backdrops> photosList) {
        mList = list;
        videoClickInterface = videoInterface;
        mPhotosList = photosList;
        mContext = context;
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoHolder(LayoutInflater.from(mContext).inflate(R.layout.video_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(VideoHolder holder, int position) {
        final VideoResults videoResult = mList.get(position);
        holder.videoView.setImageAlpha(120);
        Glide.with(mContext).load("https://image.tmdb.org/t/p/w500" + mPhotosList.get(mPhotosList.size() -  position - 1).getFile_path()).into(holder.videoView);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: video clicked!!");
                videoClickInterface.OnVideoClick(videoResult);
                }
        });
    }

    @Override
    public int getItemCount() {
        if(mList != null)
            return mList.size();
        else
            return 0;
    }

    public class VideoHolder extends RecyclerView.ViewHolder{
        ImageView videoView;
        RelativeLayout layout;
        public VideoHolder(View itemView) {
            super(itemView);
            videoView = (ImageView) itemView.findViewById(R.id.imageView_listItem_videos);
            layout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout_video);
        }
    }

}
