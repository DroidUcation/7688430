package com.goldstein.android.goandjoi;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.Date;


/**
 * Created by Goldstein on 18/04/2016.
 */
public class PrivateContentProvider extends ContentProvider {

    static final String PROVIDER_NAME = "com.goldstein.android.goandjoi";
    static final String PLACE_URI = "content://" + PROVIDER_NAME + "/" + GoAndJoyInfoContract.PlacesEntry.TABLE_NAME;
    static final String EXTENDED_PLACES_URI = "content://" + PROVIDER_NAME + "/" + GoAndJoyInfoContract.ExtendedPlacesEntry.TABLE_NAME;
    static final String USER_TRIP_URI = "content://" + PROVIDER_NAME + "/" + GoAndJoyInfoContract.UserTripEntry.TABLE_NAME;
    static final String IAMGES_URI = "content://" + PROVIDER_NAME + "/" + GoAndJoyInfoContract.ImageEntry.TABLE_NAME;


    static final Uri CONTENT_PLACE_URI = Uri.parse(PLACE_URI);
    static final Uri CONTENT_URI = Uri.parse(EXTENDED_PLACES_URI);

    static final String USER_URI = "content://" + PROVIDER_NAME + "/"+GoAndJoyInfoContract.UsersEntry.TABLE_NAME;
    static final int PLACES = 1;
    static final int EXTENDED_PLACES = 2;
    static final int USER_TRIP = 3;
    static final int USERS = 4;
    static final int IMAGES = 5;
    static final int SHOPPING_CENTERS = 6;
    static final int GAS_STATIONS = 7;
    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, GoAndJoyInfoContract.PlacesEntry.TABLE_NAME, PLACES);
        // uriMatcher.addURI(PROVIDER_NAME, GoAndJoyInfoContract.PlacesEntry.TABLE_NAME, SHOPPING_CENTERS);
        //uriMatcher.addURI(PROVIDER_NAME, GoAndJoyInfoContract.PlacesEntry.TABLE_NAME, GAS_STATIONS);
        uriMatcher.addURI(PROVIDER_NAME, GoAndJoyInfoContract.ExtendedPlacesEntry.TABLE_NAME, EXTENDED_PLACES);
        uriMatcher.addURI(PROVIDER_NAME, GoAndJoyInfoContract.ImageEntry.TABLE_NAME, IMAGES);
        uriMatcher.addURI(PROVIDER_NAME, "flowers/#", USERS);
    }

    //private static final UriMatcher sUriMatcher = buildUriMatcher();
    private SQLiteHelper sqlHelper;
    private SQLiteDatabase sqLiteDatabase;




    @Override
    public boolean onCreate() {
        sqlHelper = new SQLiteHelper(getContext());
        sqLiteDatabase = sqlHelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();


        switch (uriMatcher.match(uri)) {
            case PLACES:
                qb.setTables(GoAndJoyInfoContract.PlacesEntry.TABLE_NAME);
                break;
            case EXTENDED_PLACES:
                qb.setTables(
                        GoAndJoyInfoContract.ExtendedPlacesEntry.TABLE_NAME +
                                " ep INNER JOIN "+
                        GoAndJoyInfoContract.PlacesEntry.TABLE_NAME+" p " +
                                "ON ep."+
                                    GoAndJoyInfoContract.ExtendedPlacesEntry.COLUMN_PLACE_ID+ " = p."+
                                    GoAndJoyInfoContract.PlacesEntry.COLUMN_ID +
                                " Left JOIN "+
                        GoAndJoyInfoContract.ImageEntry.TABLE_NAME +" img " +
                                "ON p."+
                                    GoAndJoyInfoContract.PlacesEntry.COLUMN_ID + " = img."+
                                    GoAndJoyInfoContract.ImageEntry.COLUMN_PLACE_ID );
                break;
            case IMAGES:
                qb.setTables(GoAndJoyInfoContract.ImageEntry.TABLE_NAME);
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        Cursor c = qb.query(sqLiteDatabase,	projection,	selection, selectionArgs,null, null, sortOrder);

        /**
         * register to watch a content URI for changes
         */
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
       return "";// ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +  CameraInfoContract.CONTENT_AUTHORITY + "/" + CameraInfoContract.CameraInfoEntry.TABLE_NAME;

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
        int count = 0;

        switch (uriMatcher.match(uri)){
            case PLACES:
                values = new ContentValues();
                values.put(GoAndJoyInfoContract.PlacesEntry.CULUMN_MY_PLACE, "1");
                values.put(GoAndJoyInfoContract.PlacesEntry.COLUMN_DATE, new Date().getTime());

                count = sqLiteDatabase.update(GoAndJoyInfoContract.PlacesEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri );
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
