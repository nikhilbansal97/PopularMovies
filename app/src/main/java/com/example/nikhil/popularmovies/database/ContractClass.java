package com.example.nikhil.popularmovies.database;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by NIKHIL on 25-12-2017.
 */

public class ContractClass {

    public static final String AUTHORITY = "com.example.nikhil.popularmovies.database";
    public static final Uri BASE_ITEM_URI = Uri.parse("content://" + AUTHORITY);

    public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY;
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY;

    public ContractClass() {
    }

    public static class MovieProvider implements BaseColumns {

        public static final String TABLE_NAME = "MovieTable";

        public static final String PATH_TABLE = "movie";
        public static final String PATH_ID_TABLE = "movie/#";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_MOVIE_ID = "movieId";
    }

    public static class TvProvider implements BaseColumns {

        public static final String TABLE_NAME = "TvTable";

        public static final String PATH_TABLE = "tv";
        public static final String PATH_ID_TABLE = "tv/#";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TV_ID = "tvId";
    }

}
