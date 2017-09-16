package com.example.nikhil.popularmovies.database;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by nikhil on 16/9/17.
 */

@ContentProvider(authority = DatabaseProvider.AUTHORITY, database = DatabaseClass.class)
public class DatabaseProvider {

    public static final String AUTHORITY = "com.example.nikhil.popularmovies.database";

    @TableEndpoint(table = DatabaseClass.MOVIE_TABLE)
    public static class MovieTableClass {

        @ContentUri(
                path = "movie",
                type = "vnd.android.cursor.dir",
                defaultSort = MovieTable.COLUMN_ID + " ASC")
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/movie");

        @InexactContentUri(
                path = "movie/#",
                type = "vnd.content.cursor.item",
                name = "MOVIE_ID",
                whereColumn = MovieTable.COLUMN_MOVIE_ID,
                pathSegment = 1)
        public static Uri withId(String id){
            return Uri.parse("content://" + AUTHORITY + "/movie/" + id);
        }
    }

    @TableEndpoint(table = DatabaseClass.TV_TABLE)
    public static class TvTableClass {

        @ContentUri(
                path = "tv",
                type = "vnd.android.cursor.dir",
                defaultSort = TvTable.COLUMN_ID + " ASC")
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/tv");

        @InexactContentUri(
                path = "tv/#",
                type = "vnd.content.cursor.item",
                name = "TV_ID",
                whereColumn = TvTable.COLUMN_TEXT,
                pathSegment = 1)
        public static Uri withId(String id){
            return Uri.parse("content://" + AUTHORITY + "/tv/" + id);
        }
    }
}
