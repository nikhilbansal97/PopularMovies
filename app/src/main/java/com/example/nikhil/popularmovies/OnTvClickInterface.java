package com.example.nikhil.popularmovies;

import com.example.nikhil.popularmovies.pojos.tv.TvResults;

/**
 * Created by nikhil on 24/8/17.
 */

/**
 * This interface is implemented by the MainActivity to handle the tv show clicks.
 */
public interface OnTvClickInterface {
    void onTvItemClick(TvResults result);
}
