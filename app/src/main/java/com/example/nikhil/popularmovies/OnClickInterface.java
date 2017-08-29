package com.example.nikhil.popularmovies;

import com.example.nikhil.popularmovies.pojos.movie.Results;

/**
 * Created by nikhil on 11/7/17.
 */

/**
 * This interface will be implemented by the MainActivity so that the movie item clicks can be handled.
 */
public interface OnClickInterface {
    void onClick(Results movie);
}
