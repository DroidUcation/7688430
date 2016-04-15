package com.example.goldstein.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    int layoutResID = R.layout.activity_main;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void setButtons()
    {
        btn1 = btn1==null?(Button) findViewById(R.id.btn_1):btn1;
        btn2 = btn2==null?(Button) findViewById(R.id.btn_2):btn2;
        btn3 = btn3==null?(Button) findViewById(R.id.btn_3):btn3;
        btn4 = btn4==null?(Button) findViewById(R.id.btn_4):btn4;
        btn5 = btn5==null?(Button) findViewById(R.id.btn_5):btn5;
    }
    public void onBtnClick(View view) {

        setButtons();
        Intent intent =null;
        if (view.equals(btn1)) {
            intent = new Intent(this, FirstActivity.class);
            layoutResID = R.layout.activity_first;
        }
        if (view.equals(btn2)) {
            intent = new Intent(this, SecondActivity.class);
            layoutResID = R.layout.activity_second;
        }
        else if (view.equals(btn3)) {
            intent = new Intent(this, ThirdActivity.class);
            layoutResID = R.layout.activity_third;
        }
        else if (view.equals(btn4)) {
            intent = new Intent(this, ForthActivity.class);
            layoutResID = R.layout.activity_forth;
        }
        else if (view.equals(btn5)) {
            intent = new Intent(this, FifthActivity.class);
            layoutResID = R.layout.activity_fifth;
        }

        startActivity(intent);
    }

}
