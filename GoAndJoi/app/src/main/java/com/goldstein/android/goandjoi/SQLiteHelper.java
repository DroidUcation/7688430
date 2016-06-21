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

    String CreateTable  = "CREATE TABLE "+ GoAndJoyInfoContract.UsersEntry.TABLE_NAME +" (" +
            /*CameraInfoEntry.COLUMN_ID +" INTEGER PRIMERY KEY, " +
            CameraInfoEntry.COLUMN_TITLE +"  TEXT, " +
            CameraInfoEntry.COLUMN_content +" TEXT," +
            CameraInfoEntry.COLUMN_imageName +" TEXT " +*/
            ")";
    private Context context;
    private int id = 1;

    public SQLiteHelper(Context context) {
        super(context, "goandjoy.db", null, 10);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(GoAndJoyInfoContract.CREATE_PLACES_TABLE);

        for(int i=1;i<=10;i++) {
            insertPlacesEntry(db, "place " + i, "description place " + i);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+GoAndJoyInfoContract.PlacesEntry.TABLE_NAME );
        onCreate(db);
    }

    public void insertPlacesEntry(SQLiteDatabase db, String name, String description)
    {
        ContentValues values = new ContentValues();

        values.put(GoAndJoyInfoContract.PlacesEntry.COLUMN_ID, id);
        values.put(GoAndJoyInfoContract.PlacesEntry.COLUMN_ENGLISH_NAME, name);
        values.put(GoAndJoyInfoContract.PlacesEntry.COLUMN_ENGLISH_DESCRIPTION, description);
        long res = db.insert(GoAndJoyInfoContract.PlacesEntry.TABLE_NAME, null, values);
        id++;
    }

    public void insertUsersEntry(SQLiteDatabase db, String name, String description)
    {
        ContentValues values = new ContentValues();

        values.put(GoAndJoyInfoContract.UsersEntry.COLUMN_ID, id);
        id++;
    }

    public void getCameraInfo()
    {
        //String[] columns = new String[]{CameraInfoEntry.COLUMN_TITLE,CameraInfoEntry.COLUMN_content};
        Cursor cursor = getReadableDatabase().query(GoAndJoyInfoContract.PlacesEntry.TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        cursor.getString(1);
        cursor.getString(2);
        cursor.getString(3);
        int cnt = 1;
        while (cursor.moveToNext()) {
            cursor.getString(1);
            cursor.getString(2);
            cursor.getString(3);
            cnt++;
        }
    }
}
