package com.example.nikhil.popularmovies.database;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by nikhil on 16/9/17.
 */

@Database(version = DatabaseClass.VERSION)
public class DatabaseClass {

    public static final int VERSION = 1;

    @Table(MovieTable.class)
    public static final String MOVIE_TABLE = "movie_table";

    @Table(TvTable.class)
    public static final String TV_TABLE = "tv_table";
}
