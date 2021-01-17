package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void b1(View v)
    {
        Intent i=new Intent(this, oneactivity.class);
        startActivity(i);
    }

    public void b2(View v)
    {
        Intent i1=new Intent(this, twoactivity.class);
        startActivity(i1);
    }

    public void b3(View v)
    {
        Intent i2=new Intent(this, threeactivity.class);
        startActivity(i2);
    }

    public void b4(View v)
    {
        Intent i3=new Intent(this, fouractivity.class);
        startActivity(i3);
    }


    public void b5(View v)
    {

    }

    public void b6(View v)
    {

    }




}