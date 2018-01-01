package com.example.nikhil.popularmovies.database;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.simonvt.schematic.annotation.ContentUri;

/**
 * Created by NIKHIL on 27-12-2017.
 */

public class DatabaseContentProvider extends ContentProvider {

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private DatabaseHelper mDbHelper;

    private static final int MOVIE_CODE = 1000;
    private static final int MOVIE_ITEM_CODE = 1001;
    private static final int TV_CODE = 2000;
    private static final int TV_ITEM_CODE = 2001;

    static {
        uriMatcher.addURI(ContractClass.AUTHORITY, ContractClass.MovieProvider.PATH_TABLE, MOVIE_CODE);
        uriMatcher.addURI(ContractClass.AUTHORITY, ContractClass.MovieProvider.PATH_ID_TABLE, MOVIE_ITEM_CODE);
        uriMatcher.addURI(ContractClass.AUTHORITY, ContractClass.TvProvider.PATH_TABLE, TV_CODE);
        uriMatcher.addURI(ContractClass.AUTHORITY, ContractClass.TvProvider.PATH_ID_TABLE, TV_ITEM_CODE);
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        int code = uriMatcher.match(uri);
        Cursor cursor;
        switch (code) {
            case MOVIE_CODE :
                cursor = database.query(ContractClass.MovieProvider.TABLE_NAME,
                        projection, selection, selectionArgs, null, null, null, null);
                return cursor;
            case MOVIE_ITEM_CODE :
                selection = ContractClass.MovieProvider._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(ContractClass.MovieProvider.TABLE_NAME,
                        projection, selection, selectionArgs, null, null, null, null);
                return cursor;
            case TV_CODE :
                cursor = database.query(ContractClass.TvProvider.TABLE_NAME,
                        projection, selection, selectionArgs, null, null, null, null);
                return cursor;
            case TV_ITEM_CODE :
                selection = ContractClass.TvProvider._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(ContractClass.TvProvider.TABLE_NAME,
                        projection, selection, selectionArgs, null, null, null, null);
                return cursor;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int code = uriMatcher.match(uri);
        if (code == MOVIE_CODE || code == TV_CODE)
            return ContractClass.CONTENT_LIST_TYPE;
        else
            return ContractClass.CONTENT_ITEM_TYPE;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int code = uriMatcher.match(uri);
        long itemId = 0;
        switch (code) {
            case MOVIE_CODE :
                itemId = database.insert(ContractClass.MovieProvider.TABLE_NAME, null, values);
                break;
            case TV_CODE :
                itemId = database.insert(ContractClass.TvProvider.TABLE_NAME, null, values);
                break;
        }
        if (itemId == -1) {
            throw new IllegalArgumentException("Insert failed at uri " + uri.toString());
        }
        return ContentUris.withAppendedId(ContractClass.BASE_ITEM_URI, itemId);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int code = uriMatcher.match(uri);
        switch (code) {
            case MOVIE_CODE :
                return database.delete(ContractClass.MovieProvider.TABLE_NAME, null, null);
            case MOVIE_ITEM_CODE:
                selection = ContractClass.MovieProvider._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return database.delete(ContractClass.MovieProvider.TABLE_NAME, selection, selectionArgs);
            case TV_CODE :
                return database.delete(ContractClass.TvProvider.TABLE_NAME, null, null);
            case TV_ITEM_CODE:
                selection = ContractClass.TvProvider._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return database.delete(ContractClass.TvProvider.TABLE_NAME, selection, selectionArgs);
        }

        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int code = uriMatcher.match(uri);
        switch (code) {
            case MOVIE_CODE :
                return database.update(ContractClass.MovieProvider.TABLE_NAME, values, selection, selectionArgs);
            case MOVIE_ITEM_CODE :
                selection = ContractClass.MovieProvider._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return database.update(ContractClass.MovieProvider.TABLE_NAME, values, selection, selectionArgs);
            case TV_CODE :
                return database.update(ContractClass.TvProvider.TABLE_NAME, values, selection, selectionArgs);
            case TV_ITEM_CODE :
                selection = ContractClass.TvProvider._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return database.update(ContractClass.TvProvider.TABLE_NAME, values, selection, selectionArgs);
        }
        return 0;
    }
}
