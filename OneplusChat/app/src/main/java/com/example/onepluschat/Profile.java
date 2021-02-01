package com.example.onepluschat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.onepluschat.databinding.ActivityProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Profile extends AppCompatActivity {
    ActivityProfileBinding bin;
    FirebaseAuth au;
    FirebaseDatabase d;
    FirebaseStorage s;
    Uri im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(bin.getRoot());

        d = FirebaseDatabase.getInstance();
        s = FirebaseStorage.getInstance();
        au = FirebaseAuth.getInstance();

        bin.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setAction(Intent.ACTION_GET_CONTENT);
                in.setType("image/*");
                startActivityForResult(in, 23);
            }
        });

        bin.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = bin.Name.getText().toString();

                if(name.isEmpty()) {
                    bin.Name.setError("Please Enter Name");
                    return;
                }

                if(im != null) {
                    StorageReference ref = s.getReference().child("Profiles").child(au.getUid());
                    ref.putFile(im).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if(task.isSuccessful()) {
                                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String imageU = uri.toString();
                                        String useid = au.getUid();
                                        String phone = au.getCurrentUser().getPhoneNumber();
                                        String name = bin.Name.getText().toString();

                                        user ui = new user(useid, name, phone, imageU);

                                        d.getReference()
                                                .child("User")
                                                .child(useid)
                                                .setValue(ui)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {

                                                        Intent intent = new Intent(Profile.this, MainActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                });
                                    }
                                });
                            }
                        }
                    });
                } else {
                    String userid = au.getUid();
                    String phone = au.getCurrentUser().getPhoneNumber();
                    String na = bin.Name.getText().toString();
                    user u = new user(userid, na, phone, "No Image");

                    d.getReference()
                            .child("User")
                            .child(userid)
                            .setValue(u)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Intent intent = new Intent(Profile.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                }

            }
        });
    }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if(data != null) {
                if(data.getData() != null) {
                    Uri ur= data.getData();
                    FirebaseStorage storage = FirebaseStorage.getInstance();

                }
            }
            bin.imageView.setImageURI(data.getData());
            im = data.getData();
        }
}
