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
        String s="one";
        Intent i=new Intent(this, oneactivity.class);
        String s1="Time Table for Monday";
        startActivity(i);
    }

    public void b2(View v)
    {

    }

    public void b3(View v)
    {

    }

    public void b4(View v)
    {

    }


    public void b5(View v)
    {

    }

    public void b6(View v)
    {

    }




}