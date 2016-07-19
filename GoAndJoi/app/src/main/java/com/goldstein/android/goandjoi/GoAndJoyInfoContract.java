package com.goldstein.android.goandjoi;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Goldstein on 18/04/2016.
 */
public class GoAndJoyInfoContract {

    public static final String PROVIDER_NAME = "com.goldstein.android.goandjoi";
    static final String PLACE_URI = "content://" + PROVIDER_NAME + "/" + GoAndJoyInfoContract.PlacesEntry.TABLE_NAME;
    static final String EXTENDED_PLACES_URI = "content://" + PROVIDER_NAME + "/" + GoAndJoyInfoContract.ExtendedPlacesEntry.TABLE_NAME;
    static final String IAMGES_URI = "content://" + PROVIDER_NAME + "/" + GoAndJoyInfoContract.ImageEntry.TABLE_NAME;
    static final String USER_TRIP_URI = "content://" + PROVIDER_NAME + "/" + GoAndJoyInfoContract.UserTripEntry.TABLE_NAME;
    static final String USER_URI = "content://" + PROVIDER_NAME + "/" + GoAndJoyInfoContract.UsersEntry.TABLE_NAME;

    public static final Uri CONTENT_URI = Uri.parse(EXTENDED_PLACES_URI);
    public static final Uri CONTENT_PLACE_URI = Uri.parse(PLACE_URI);
    public static final Uri UPDATE_PLACE_URI = Uri.parse(EXTENDED_PLACES_URI);

    public static final String CREATE_USER_TABLE = "CREATE TABLE " + UsersEntry.TABLE_NAME + " (" +
            UsersEntry.COLUMN_ID + " INTEGER PRIMARY KEY, " +
            UsersEntry.COLUMN_NAME + "  TEXT, " +
            UsersEntry.COLUMN_PERMISSION + " TEXT," +
            UsersEntry.COLUMN_PERMISSION_FROM + " TEXT, " +
            UsersEntry.COLUMN_PERMISSION_TO + " TEXT " +
            ")";

    public static final String CREATE_PLACES_TABLE = "CREATE TABLE "+ PlacesEntry.TABLE_NAME +" (" +
            PlacesEntry.COLUMN_ID +" INTEGER PRIMARY KEY, " +
            PlacesEntry.COLUMN_TITLE +"  TEXT, " +
            PlacesEntry.COLUMN_ENGLISH_NAME +" TEXT," +
            PlacesEntry.COLUMN_HEBREW_NAME +" TEXT, " +
            PlacesEntry.COLUMN_ENGLISH_DESCRIPTION +" TEXT, " +
            PlacesEntry.COLUMN_HEBREW_DESCRIPTION +" TEXT, " +
            PlacesEntry.COLUMN_CATEGORY_TYPE +" INTEGER, " +
            PlacesEntry.COLUMN_LOCATION +"  TEXT, " +
            PlacesEntry.CULUMN_MY_PLACE +"  INTEGER, " +
            PlacesEntry.COLUMN_DATE +"  DATE " +
            ")";

    public static final String CREATE_EXTENDED_PLACES_TABLE = "CREATE TABLE "+ ExtendedPlacesEntry.TABLE_NAME +" (" +
            ExtendedPlacesEntry.COLUMN_ID +" INTEGER PRIMARY KEY, " +
            ExtendedPlacesEntry.COLUMN_PLACE_ID +" INTEGER , " +
            ExtendedPlacesEntry.COLUMN_DURATION_TIME_TYPE +"  INTEGER, " +
            ExtendedPlacesEntry.COLUMN_ACCESSIBILITY_TYPE +" INTEGER," +
            ExtendedPlacesEntry.COLUMN_TRIP_TYPE +" INTEGER, " +
            ExtendedPlacesEntry.COLUMN_TRIP_LEVEL_TYPE +" INTEGER " +
            ")";
    public static final String CREATE_IMAGES_TABLE = "CREATE TABLE " + ImageEntry.TABLE_NAME + " (" +
            ImageEntry.COLUMN_ID + " INTEGER PRIMARY KEY, " +
            ImageEntry.COLUMN_IMAGE_URI + "  TEXT, " +
            ImageEntry.COLUMN_PLACE_ID + " INTEGER" +

            ")";
    public static final String CREATE_USER_TRIP_TABLE = "CREATE TABLE " + UserTripEntry.TABLE_NAME + " (" +
            UserTripEntry.COLUMN_ID + " INTEGER PRIMARY KEY, " +
            UserTripEntry.COLUMN_USER_ID + "  INTEGER, " +
            UserTripEntry.COLUMN_PLACE_ID + " INTEGER," +
            UserTripEntry.COLUMN_TRIP_DATE + " DATE, " +
            UserTripEntry.COLUMN_PICTURE + " TEXT " +
            ")";

    public static class UsersEntry implements BaseColumns
    {
        public static final String TABLE_NAME ="UserInfo";

        public static final String COLUMN_ID= "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PERMISSION = "permission";
        public static final String COLUMN_PERMISSION_FROM = "permission_from";
        public static final String COLUMN_PERMISSION_TO= "permission_to";
    }

    public static class ImageEntry implements BaseColumns {
        public static final String TABLE_NAME = "imagesInfo";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_PLACE_ID = "place_id";
        public static final String COLUMN_IMAGE_URI = "image_uri";
    }

    public static class PlacesEntry implements BaseColumns {
        public static final String TABLE_NAME = "PlacesInfo";

        public static final String COLUMN_ID= "_id";
        public static final String COLUMN_TITLE = "name";
        public static final String COLUMN_ENGLISH_NAME = "english_name";
        public static final String COLUMN_HEBREW_NAME = "hebrew_name";
        public static final String COLUMN_ENGLISH_DESCRIPTION = "english_description";
        public static final String COLUMN_HEBREW_DESCRIPTION = "hebrew_description";
        public static final String COLUMN_CATEGORY_TYPE = "category_type";
        public static final String COLUMN_LOCATION = "location";
        public static final String CULUMN_MY_PLACE = "my_place";
        public static final String COLUMN_DATE = "date";
    }

    public static class ExtendedPlacesEntry implements BaseColumns
    {
        public static final String TABLE_NAME ="ExtendedPlaceInfo";

        public static final String COLUMN_ID= "_id";
        public static final String COLUMN_PLACE_ID= "place_id";
        public static final String COLUMN_DURATION_TIME_TYPE = "duration_time_type";
        public static final String COLUMN_ACCESSIBILITY_TYPE = "accessibility_type";
        public static final String COLUMN_TRIP_TYPE = "trip_type";
        public static final String COLUMN_TRIP_LEVEL_TYPE= "trip_level_type";
    }

    public static class UserTripEntry implements BaseColumns
    {
        public static final String TABLE_NAME ="UserTripInfo";

        public static final String COLUMN_ID= "_id";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_PLACE_ID = "place_id";
        public static final String COLUMN_TRIP_DATE = "trip_date";
        public static final String COLUMN_PICTURE= "picture";
    }
}
