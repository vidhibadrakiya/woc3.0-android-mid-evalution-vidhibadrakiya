package com.example.onepluschat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.onepluschat.databinding.ActivityPhonenumberBinding;
import com.google.firebase.auth.FirebaseAuth;

public class phonenumber extends AppCompatActivity {
    ActivityPhonenumberBinding b;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b=ActivityPhonenumberBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null) {
            Intent intent = new Intent(phonenumber.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        b.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i=new Intent(phonenumber.this,OTP.class);
            i.putExtra("Number", b.editTextPhone.getText().toString());
            startActivity(i);
            }
        });

    }
}