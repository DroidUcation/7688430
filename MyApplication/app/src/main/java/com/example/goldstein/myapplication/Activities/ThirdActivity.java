package com.example.goldstein.myapplication.Activities;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.goldstein.myapplication.CameraInfoContract;
import com.example.goldstein.myapplication.R;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        String[] projection = new String[]{
                CameraInfoContract.CameraInfoEntry._ID,
                CameraInfoContract.CameraInfoEntry.COLUMN_content,
                CameraInfoContract.CameraInfoEntry.COLUMN_imageName};

        Cursor cursor = getContentResolver().query(CameraInfoContract.BASE_CONTENT_URI, projection, CameraInfoContract.CameraInfoEntry._ID+"=?", new String[]{"3"}, null);
        int columnContentIdx = cursor.getColumnIndex(CameraInfoContract.CameraInfoEntry.COLUMN_content);
        int columnImageIdx = cursor.getColumnIndex(CameraInfoContract.CameraInfoEntry.COLUMN_imageName);

        int  i = 0;
        if(cursor != null) {
            cursor.moveToFirst();
            ((TextView) findViewById(R.id.at_text_view_content)).setText(cursor.getString(columnContentIdx));
            ((ImageView) findViewById(R.id.at_image_view)).setImageResource(cursor.getInt(columnImageIdx));
        }

    }

}
