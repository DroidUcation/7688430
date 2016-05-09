package com.example.goldstein.myapplication.Providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.support.annotation.Nullable;

import com.example.goldstein.myapplication.Contracts.CameraInfoContract;
import com.example.goldstein.myapplication.SQLHelpers.SQLHelper;

/**
 * Created by Goldstein on 18/04/2016.
 */
public class CameraProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private SQLHelper sqlHelper;

    static UriMatcher buildUriMatcher() {
        // I know what you're thinking.  Why create a UriMatcher when you can use regular
        // expressions instead?  Because you're not crazy, that's why.

        // All paths added to the UriMatcher have a corresponding code to return when a match is
        // found.  The code passed into the constructor represents the code to return for the root
        // URI.  It's common to use NO_MATCH as the code for this case.
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = CameraInfoContract.CONTENT_AUTHORITY;

        // For each type of URI you want to add, create a corresponding code.
        matcher.addURI(authority, CameraInfoContract.PATH_CAMERA, 100);
        //matcher.addURI(authority, CameraInfoContract.PATH_CAMERA + "/*", WEATHER_WITH_LOCATION);
        //matcher.addURI(authority, CameraInfoContract.PATH_CAMERA + "/*/#", WEATHER_WITH_LOCATION_AND_DATE);

        //matcher.addURI(authority, WeatherContract.PATH_LOCATION, LOCATION);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        sqlHelper = new SQLHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, CancellationSignal cancellationSignal) {
        return super.query(uri, projection, selection, selectionArgs, sortOrder, cancellationSignal);
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        retCursor = sqlHelper.getReadableDatabase().query(
                CameraInfoContract.CameraInfoEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );/*
        switch (sUriMatcher.match(uri)) {

            case 100: {
                retCursor = sqlHelper.getReadableDatabase().query(
                        CameraInfoContract.CameraInfoEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }*/
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
