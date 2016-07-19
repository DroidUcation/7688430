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
        super(context, "goandjoy.db", null, 26);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(GoAndJoyInfoContract.CREATE_PLACES_TABLE);
        db.execSQL(GoAndJoyInfoContract.CREATE_EXTENDED_PLACES_TABLE);
        db.execSQL(GoAndJoyInfoContract.CREATE_USER_TRIP_TABLE);
        db.execSQL(GoAndJoyInfoContract.CREATE_USER_TABLE);
		db.execSQL(GoAndJoyInfoContract.CREATE_IMAGES_TABLE);

        insertAttractions(db);
        insertHikings(db);

       /* insertExtendedPlacesEntry(db,1, "The Western Wall", "The Western Wall is a holy site\n" +
                "We therefore ask you to comply with accepted standards of behavior during your visit to the Western Wall area. " ,1,1,1,1, "31.7767234,35.2366972");
        insertExtendedPlacesEntry(db,2, "My Home ", "description place " ,1,1,1,1, "31.7859625,35.1994182");
        insertExtendedPlacesEntry(db,3, "My Work ", "bank hamizrahi " ,1,1,1,1,"31.9604499,34.9010827" );*/
              /*  for (int i = 1; i <= 5; i++) {
            insertExtendedPlacesEntry(db, i , "atraction " + i, "description place " + i, 1, 1, 1, 1, "31.9604499,34.9010827", Enums.CategoryTypes.ATTRACTIONS);
        }*/
            insertPlacesEntry(db, 7, "Azrieli Towers", "Shopping center in the  tallest  towers in Tel Aviv\n", "32.0744261,34.7942252", Enums.CategoryTypes.SHOPPING_CENTERS);
            insertPlacesEntry(db, 8, "Ayalon Mall", "A large shopping center\n", " 32.100891,34.828865", Enums.CategoryTypes.SHOPPING_CENTERS);
        insertPlacesEntry(db, 9, "Primark Vienna", "Large clothing store where you can buy clothes in Austria", "48.1091685,16.3204753", Enums.CategoryTypes.SHOPPING_CENTERS);
        insertPlacesEntry(db, 10, "PAZ", "", "31.7760143,35.2766891", Enums.CategoryTypes.GAS_STATIONS);
        insertPlacesEntry(db, 11, "GIBORRI ISRAEL", "", "32.0714606,34.7966165", Enums.CategoryTypes.GAS_STATIONS);
        insertPlacesEntry(db, 12, "SONOL", "", "31.7762678,35.2766893", Enums.CategoryTypes.GAS_STATIONS);
        insertPlacesEntry(db, 13, "TAL HAGESHER", "", "32.0731895,34.7906387", Enums.CategoryTypes.GAS_STATIONS);


        /*for (int i = 13; i <= 18; i++) {
            insertPlacesEntry(db, i, "gas station"+i, "", "31.9604499,34.9010827", Enums.CategoryTypes.GAS_STATIONS);
        }*/
        /*
        for (int i = 20; i <= 24; i++) {
            insertPlacesEntry(db, i, "SUPERMARKETS "+i, "description supermarket", "31.9604499,34.9010827", Enums.CategoryTypes.SUPERMARKETS);
        }*/
       /* for (int i = 25; i <= 30; i++) {
            insertExtendedPlacesEntry(db, i , "TRACKS " + i, "description place " + i, 1, 1, 1, 1, "31.9604499,34.9010827", Enums.CategoryTypes.TRACKS);
        }*/

    }

    private void insertHikings(SQLiteDatabase db) {
        insertExtendedPlacesEntry(db,4, "Stuibenfall",
                "The Stuibenfall is the most impressive and beautiful waterfall of the Tyrol, tumbling down 159 m over two steps. Accessible from the car park at the Ötzi village in 30 minutes, you can then walk along the waterfall to the Stuibenfall lodge (approx. 50 minutes).\n" ,
                1,1,1,1,
                "47.1267419,10.9513568",
                Enums.CategoryTypes.TRACKS);
        insertImgesEntry(db,"img_1",4);

        insertExtendedPlacesEntry(db,5, "Piberger See",
                "A beautiful lake, with stunning mountain vistas all around. A very nice wooded trail around the lake. You can rent a boat and enjoy the lake.",
                1,1,1,1,
                "47.1933101,10.8971104",
                Enums.CategoryTypes.TRACKS);
        insertImgesEntry(db,"img_p_s_1",5);

        insertExtendedPlacesEntry(db,6, "Leutasch Klamm",
                "Great views and a nice walk, also for kids. Easy to access. Recommended to go in the morning as it is getting packed around noon.\n" ,
                1,1,1,1,
                "47.1933101,10.8971104",
                Enums.CategoryTypes.TRACKS);
        insertImgesEntry(db,"img_l_1",6);

        insertExtendedPlacesEntry(db,7, "Solden",
                "You go up an a cable car to 3000 meters high, that you can enjoy the incredible view, and then you can go down to the village in a route which take about 3 hours, or you can take back the cable car.",
                1,1,1,1,
                "46.9607765,11.0190478",
                Enums.CategoryTypes.TRACKS);
        insertImgesEntry(db,"img_5",7);
    }

    private void insertAttractions(SQLiteDatabase db) {
        insertExtendedPlacesEntry(db,1, "The Western Wall", "The Western Wall is a holy site\n" +
                "We therefore ask you to comply with accepted standards of behavior during your visit to the Western Wall area. " ,1,1,1,1, "31.7767234,35.2366972", Enums.CategoryTypes.ATTRACTIONS);
        insertImgesEntry(db,"the_wall",1);
        insertExtendedPlacesEntry(db,2, "Rafting & Kajakschule Ötztal Bruno Strigl",
                "Three hours of extreme in the water, you will have a guide that will take you through the river. Start getting ready! Posibble for children from 12 years old" ,
                1,1,1,1,
                "47.2157997,10.8681074",
                Enums.CategoryTypes.ATTRACTIONS);
        insertImgesEntry(db,"img_2",2);

        insertExtendedPlacesEntry(db,3, "Hoch-Imst",
                "The mountain slide will take you from the top of the mountain  until the bottom." ,
                1,1,1,1,
                "47.2414873,10.7405496",
                Enums.CategoryTypes.ATTRACTIONS);
        insertImgesEntry(db,"img_3",3);
        insertImgesEntry(db,"img_4",3);
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

    public long insertPlacesEntry(SQLiteDatabase db, int placeId, String name, String description, String location, int category) {
        ContentValues values = new ContentValues();
        values.put(GoAndJoyInfoContract.PlacesEntry.COLUMN_CATEGORY_TYPE, category);
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
                                          String location, int category) {
        long newPlaceId = insertPlacesEntry(db, placeId, name, description, location, category);
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
