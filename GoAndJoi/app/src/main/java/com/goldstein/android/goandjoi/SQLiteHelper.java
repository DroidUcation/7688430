package com.goldstein.android.goandjoi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Goldstein on 06/06/2016.
 */
public class SQLiteHelper extends SQLiteOpenHelper {


    private Context context;
    //private static int placeId = 1;
    private int extendedPlaceId = 1;
    private int userTripId = 1;
    private int userId = 1;
    private int ImageId = 1;
    public SQLiteHelper(Context context) {
        super(context, "goandjoy.db", null, 18);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(GoAndJoyInfoContract.CREATE_PLACES_TABLE);
        db.execSQL(GoAndJoyInfoContract.CREATE_EXTENDED_PLACES_TABLE);
        db.execSQL(GoAndJoyInfoContract.CREATE_USER_TRIP_TABLE);
        db.execSQL(GoAndJoyInfoContract.CREATE_USER_TABLE);
		db.execSQL(GoAndJoyInfoContract.CREATE_IMAGES_TABLE);

        insertExtendedPlacesEntry(db,1, "The Western Wall", "The Western Wall is a holy site\n" +
                "We therefore ask you to comply with accepted standards of behavior during your visit to the Western Wall area. " ,1,1,1,1, "31.7767234,35.2366972");
        insertExtendedPlacesEntry(db,2, "My Home ", "description place " ,1,1,1,1, "31.7859625,35.1994182");
        insertExtendedPlacesEntry(db,3, "My Work ", "bank hamizrahi " ,1,1,1,1,"31.9604499,34.9010827" );
        for(int i=1;i<=5;i++) {
            insertExtendedPlacesEntry(db,i+3, "place " + i, "description place " + i,1,1,1,1, "31.9604499,34.9010827");
        }
        insertImgesEntry(db,String.valueOf(R.drawable.img_0780_1),1);
        insertImgesEntry(db,String.valueOf(R.drawable.img_0807_1),1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+GoAndJoyInfoContract.PlacesEntry.TABLE_NAME );
        db.execSQL("DROP TABLE IF EXISTS "+GoAndJoyInfoContract.ExtendedPlacesEntry.TABLE_NAME );
        db.execSQL("DROP TABLE IF EXISTS "+GoAndJoyInfoContract.UserTripEntry.TABLE_NAME );
        db.execSQL("DROP TABLE IF EXISTS "+GoAndJoyInfoContract.UsersEntry.TABLE_NAME );
        db.execSQL("DROP TABLE IF EXISTS "+GoAndJoyInfoContract.ImageEntry.TABLE_NAME );
        onCreate(db);
    }

    public long insertPlacesEntry(SQLiteDatabase db, int placeId, String name, String description, String location)
    {
        ContentValues values = new ContentValues();

        values.put(GoAndJoyInfoContract.PlacesEntry.COLUMN_ID, placeId);
        values.put(GoAndJoyInfoContract.PlacesEntry.COLUMN_ENGLISH_NAME, name);
        values.put(GoAndJoyInfoContract.PlacesEntry.COLUMN_ENGLISH_DESCRIPTION, description);
        values.put(GoAndJoyInfoContract.PlacesEntry.COLUMN_LOCATION, location);
        long res = db.insert(GoAndJoyInfoContract.PlacesEntry.TABLE_NAME, null, values);
        //placeId++;
        return  res;
    }
    public long insertImgesEntry(SQLiteDatabase db, String uri, int placeId)
    {
        ContentValues values = new ContentValues();

        values.put(GoAndJoyInfoContract.ImageEntry.COLUMN_ID, ImageId);
        values.put(GoAndJoyInfoContract.ImageEntry.COLUMN_IMAGE_URI, uri);
        values.put(GoAndJoyInfoContract.ImageEntry.COLUMN_PLACE_ID, placeId);
        long res = db.insert(GoAndJoyInfoContract.ImageEntry.TABLE_NAME, null, values);
        ImageId++;
        return  res;
    }
    public long insertExtendedPlacesEntry(SQLiteDatabase db, int placeId, String name, String description,
                                          int durationTimeType, int accessibilityType, int tripType, int tripLevelType,
                                          String location)
    {
        long newPlaceId = insertPlacesEntry(db,placeId,name,description,location);
        ContentValues values = new ContentValues();

        values.put(GoAndJoyInfoContract.ExtendedPlacesEntry.COLUMN_ID, extendedPlaceId);
        values.put(GoAndJoyInfoContract.ExtendedPlacesEntry.COLUMN_PLACE_ID, newPlaceId);
        values.put(GoAndJoyInfoContract.ExtendedPlacesEntry.COLUMN_DURATION_TIME_TYPE, durationTimeType);
        values.put(GoAndJoyInfoContract.ExtendedPlacesEntry.COLUMN_ACCESSIBILITY_TYPE, accessibilityType);
        values.put(GoAndJoyInfoContract.ExtendedPlacesEntry.COLUMN_TRIP_TYPE, tripType);
        values.put(GoAndJoyInfoContract.ExtendedPlacesEntry.COLUMN_TRIP_LEVEL_TYPE, tripLevelType);
        long res = db.insert(GoAndJoyInfoContract.ExtendedPlacesEntry.TABLE_NAME, null, values);
        extendedPlaceId++;
        return res;
    }

    public void insertUsersEntry(SQLiteDatabase db, String name, String description)
    {
        ContentValues values = new ContentValues();

        values.put(GoAndJoyInfoContract.UsersEntry.COLUMN_ID, userId);
        userId++;
    }
}
