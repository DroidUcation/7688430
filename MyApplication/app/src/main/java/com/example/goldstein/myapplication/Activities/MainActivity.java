package com.example.goldstein.myapplication.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.goldstein.myapplication.CameraInfoContract;
import com.example.goldstein.myapplication.R;

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
        int columnTitleIdx = cursor.getColumnIndex(CameraInfoContract.CameraInfoEntry.COLUMN_TITLE);
        String[] title = new String[5];
        int  i = 0;
        if(cursor != null) {
            cursor.moveToFirst();
                do {
                    title[i] = cursor.getString(columnTitleIdx);
                    i++;
                }while (cursor.moveToNext());
        }

        btn1.setText(title[0]);
        btn2.setText(title[1]);
        btn3.setText(title[2]);
        btn4.setText(title[3]);
        btn5.setText(title[4]);
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
