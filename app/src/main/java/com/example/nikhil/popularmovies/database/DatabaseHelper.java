package com.example.nikhil.popularmovies.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by NIKHIL on 27-12-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "popularmovies.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String MOVIE_CREATE_QUERY = "CREATE TABLE " + ContractClass.MovieProvider.TABLE_NAME + " ( " +
                ContractClass.MovieProvider._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ContractClass.MovieProvider.COLUMN_MOVIE_ID + " INTEGER NOT NULL);";
        String TV_CREATE_QUERY = "CREATE TABLE " + ContractClass.TvProvider.TABLE_NAME + " ( " +
                ContractClass.TvProvider._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ContractClass.TvProvider.COLUMN_TV_ID + " INTEGER NOT NULL);";
        db.execSQL(MOVIE_CREATE_QUERY);
        db.execSQL(TV_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
