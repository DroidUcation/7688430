package com.example.goldstein.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.goldstein.myapplication.CameraInfoContract.CameraInfoEntry;
import com.example.goldstein.myapplication.R;

/**
 * Created by Goldstein on 18/04/2016.
 */
public class SQLHelper extends SQLiteOpenHelper {

    String CreateTable  = "CREATE TABLE "+ CameraInfoEntry.TABLE_NAME +" (" +
                    CameraInfoEntry.COLUMN_ID +" INTEGER PRIMERY KEY, " +
                    CameraInfoEntry.COLUMN_TITLE +"  TEXT, " +
                    CameraInfoEntry.COLUMN_content +" TEXT," +
                    CameraInfoEntry.COLUMN_imageName +" TEXT " +
                    ")";
    private Context context;
    private int id = 1;

    public SQLHelper(Context context) {
        super(context, "cameras.db", null, 7);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateTable);

        insertCameraInfo(db, context.getResources().getString(R.string.btn_1_text),
                context.getResources().getString(R.string.first_str),
                R.drawable.first_img);
        insertCameraInfo(db, context.getResources().getString(R.string.btn_2_text),
                context.getResources().getString(R.string.second_str),
                R.drawable.second_img);
        insertCameraInfo(db, context.getResources().getString(R.string.btn_3_text),
                context.getResources().getString(R.string.third_str),
                R.drawable.third_img);
        insertCameraInfo(db, context.getResources().getString(R.string.btn_4_text),
                context.getResources().getString(R.string.forth_str),
                R.drawable.forth_img);
        insertCameraInfo(db, context.getResources().getString(R.string.btn_5_text),
                context.getResources().getString(R.string.fifth_str),
                R.drawable.fifth_img);

       // insertCameraInfo(db, context.getResources().getString(R.string.btn_1_text), "first_str", "first_img");
        /*insertCameraInfo(db, "btn_1_text", "second_str", Resources.getSystem().getDrawable(R.drawable.second_img,null).toString());
        insertCameraInfo(db, "btn_1_text", "third_str", Resources.getSystem().getDrawable(R.drawable.third_img,null).toString());
        insertCameraInfo(db, "btn_1_text", "forth_str", Resources.getSystem().getDrawable(R.drawable.forth_img,null).toString());
        insertCameraInfo(db, "btn_1_text", "fifth_str", Resources.getSystem().getDrawable(R.drawable.fifth_img, null).toString());
*/}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CameraInfo");
        onCreate(db);
    }

    public void insertCameraInfo(SQLiteDatabase db, String title, String content, int imageName)
    {
        ContentValues values = new ContentValues();
        
        values.put(CameraInfoEntry.COLUMN_ID, id);
        values.put(CameraInfoEntry.COLUMN_TITLE, title);
        values.put(CameraInfoEntry.COLUMN_content, content);
        values.put(CameraInfoEntry.COLUMN_imageName, imageName);
        long res = db.insert(CameraInfoEntry.TABLE_NAME, null, values);
        id++;
    }


    public void getCameraInfo()
    {
        //String[] columns = new String[]{CameraInfoEntry.COLUMN_TITLE,CameraInfoEntry.COLUMN_content};
        Cursor cursor = getReadableDatabase().query(CameraInfoEntry.TABLE_NAME, null, null, null, null, null, null);
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
