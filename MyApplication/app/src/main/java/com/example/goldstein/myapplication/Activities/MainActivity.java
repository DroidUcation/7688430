package com.example.goldstein.myapplication.Activities;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.goldstein.myapplication.Contracts.CameraInfoContract;
import com.example.goldstein.myapplication.R;
import com.example.goldstein.myapplication.SQLHelpers.SQLHelper;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setButtons();

        String[] projection = new String[]{
                CameraInfoContract.CameraInfoEntry._ID,
                CameraInfoContract.CameraInfoEntry.COLUMN_TITLE};

        Cursor cursor = getContentResolver().query(CameraInfoContract.BASE_CONTENT_URI, projection, null, null, null);
        int cnt = 1;
        String title ="empty";
        if(cursor != null) {
            if (cursor.moveToFirst()) {
                 title = cursor.getString(1);
                String content1 = cursor.getString(2);
                String image1 = cursor.getString(3);
            }
            while (cursor.moveToNext()) {
                 title = cursor.getString(1);
                String content = cursor.getString(2);
                String image = cursor.getString(3);
                cnt++;
            }
        }

        btn1.setText(title);
        /*SQLHelper sqlHelper = new SQLHelper(this);
        sqlHelper.insertCameraInfo(sqlHelper.getWritableDatabase(), "2 thing", "lalalalal", "image2");
sqlHelper.getCameraInfo();*/
       // sqlHelper.onCreate(sqlHelper.getWritableDatabase());
    }

    private void setButtons()
    {
        btn1 = btn1==null?(Button) findViewById(R.id.btn_1):btn1;
        btn2 = btn2==null?(Button) findViewById(R.id.btn_2):btn2;
        btn3 = btn3==null?(Button) findViewById(R.id.btn_3):btn3;
        btn4 = btn4==null?(Button) findViewById(R.id.btn_4):btn4;
        btn5 = btn5==null?(Button) findViewById(R.id.btn_5):btn5;

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        setButtons();
        int resId = v.getId();
        Intent intent =null;
        switch (resId)
        {
            case R.id.btn_1:
            {
                intent = new Intent(this, FirstActivity.class);
                break;
            }
            case R.id.btn_2:
            {
                intent = new Intent(this, SecondActivity.class);
                break;
            }
            case R.id.btn_3:
            {
                intent = new Intent(this, ThirdActivity.class);
                break;
            }
            case R.id.btn_4:
            {
                intent = new Intent(this, ForthActivity.class);
                break;
            }
            case R.id.btn_5:
            {
                intent = new Intent(this, FifthActivity.class);
                break;
            }
        }
        startActivity(intent);
    }
}
